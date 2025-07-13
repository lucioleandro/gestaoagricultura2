package br.com.smart4.gestaoagriculturaapi.report.controllers;

import br.com.smart4.gestaoagriculturaapi.report.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/report")
public class ReportController {

	private final ReportService reportService;
	
	private final ResourceLoader loader;

	public ReportController(ReportService reportService, ResourceLoader loader) {
		this.reportService = reportService;
		this.loader = loader;
	}

	@GetMapping("/showreport")
	public void showReport(@Param(value = "nomeRelatorio") String nomeRelatorio,
						   @RequestParam(value = "parametros") Map<String, Object> parametros,
						   final HttpServletResponse response)  {
		
			try (Connection conn = reportService.getConnection()) {
				
				String pathCabecalho = loader.getResource("classpath:/static/relatorios/cabecalho.jasper").getURL().getPath();
				String pathCabecalhoPaisagem = loader.getResource("classpath:/static/relatorios/cabecalhoPaisagem.jasper").getURL().getPath();
				String path = loader.getResource("classpath:/static/relatorios/" + nomeRelatorio + ".jasper").getURL().getPath();

				parametros = corrigeTipagemParametros(parametros);
				parametros.put("pathCabecalho", pathCabecalho);
				parametros.put("pathCabecalhoPaisagem", pathCabecalhoPaisagem);
				
				JasperPrint relatorioGerado = JasperFillManager.fillReport(path, parametros, conn);
				byte[] arquivo = JasperExportManager.exportReportToPdf(relatorioGerado);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline");
				response.setContentLengthLong(arquivo.length);
				
				OutputStream output = response.getOutputStream();
				output.write(arquivo, 0, arquivo.length);
				output.flush();
				output.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private Map<String, Object> corrigeTipagemParametros(Map<String, Object> parametros) throws IOException {
		HashMap<String, Object> parametrosCorrigidos = new HashMap<>();
		
		for(String chave:  parametros.keySet()) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> parametro = (HashMap<String, Object>) parametros.get(chave);
			
			Set<String> parametroChaves = parametro.keySet();
			
			for(String chaveParametro : parametroChaves) {
				switch (chaveParametro) {
				case "Long":
					Long longue = Long.valueOf(parametro.get(chaveParametro).toString());
					parametrosCorrigidos.put(chave, longue);
					break;
				case "String":
					String string = parametro.get(chaveParametro).toString();
					parametrosCorrigidos.put(chave, string);
					break;
				case "Integer":
					Integer integer = Integer.valueOf(parametro.get(chaveParametro).toString());
					parametrosCorrigidos.put(chave, integer);
					break;
				case "Boolean":
					Boolean boleano = Boolean.valueOf((parametro.get(chaveParametro).toString()));
					parametrosCorrigidos.put(chave, boleano);
					break;
				case "Subreport":
					String relatorio = parametro.get(chaveParametro).toString();
					String subreport = loader.getResource("classpath:/static/relatorios/"+relatorio+".jasper")
					.getURL().getPath(); 
					
					parametrosCorrigidos.put(chave, subreport);
					break;
				default:
					break;
				}
			}
		}
		
		return parametrosCorrigidos;
	}
}
