package br.com.smart4.gestaoagriculturaapi.sistema.controller;

import br.com.smart4.gestaoagriculturaapi.sistema.domain.Compativeis;
import br.com.smart4.gestaoagriculturaapi.sistema.service.CompativeisService;
import br.com.smart4.gestaoagriculturaapi.sistema.service.ParametrosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compativeis")
public class CompativeisController  {
	
	private String versaoApp = "1.0.0";
	
	@Autowired
	private CompativeisService compativeisService;
	
	@Autowired
	private ParametrosService parametrosService;

	@PostMapping("/cadastra")
	public ResponseEntity<?> cadastraCompativeis(@Valid @RequestBody Compativeis request) {
		try {
			return ResponseEntity.created(null).body(compativeisService.create(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/atualiza")
	public ResponseEntity<?> atualizaCompativeis(@RequestBody Compativeis request) {
		try {
			return ResponseEntity.ok().body(compativeisService.atualiza(request));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/lista")
	public List<Compativeis> getListaCompativeises() {
		try {
			return compativeisService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeCompativeis(@PathVariable Long id) {
		try {
			Optional<Compativeis> compativeis = compativeisService.findById(id);

			if (compativeis.isPresent()) {
				compativeisService.remove(compativeis.get());
				return ResponseEntity.ok().body("");
			} else if (!compativeis.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe esse registro no banco de dados");
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/verificacompatibilidadeaplicacaoebase")
	public ResponseEntity<?>verificaSeaplicacaoEBaseCompativeis(@Param(value="versaoFront") String versaoFront) {
		try {
			Compativeis compativeis = compativeisService.findAll().get(0);

			if(aplicacaoEBaseSaoCompativeis(compativeis, versaoFront)) {
				return ResponseEntity.ok().body(true);
			} 
				
			return ResponseEntity.ok().body(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	private boolean aplicacaoEBaseSaoCompativeis(Compativeis compativeis, String versaoFront) {
		String versaoLiberada = compativeis.getVersaoLiberada();
		
		String versaoDaBaseDeDados = versaoLiberada.substring(0, 3);
		String versaoDaAplicacao = this.versaoApp.substring(0, 3);
		String versaoDoFront = versaoFront.substring(0, 3);
		
		if(versaoDaAplicacao.equals(versaoDaBaseDeDados) 
				&& versaoDaAplicacao.equals(versaoDoFront)) {
			return true;
		}
		return false;
	}

	@GetMapping("/verificaseestainadimplente")
	public ResponseEntity<?> verificaSeEstaInadimplente() {
		try {
			Compativeis compativeis = compativeisService.findAll().get(0);

			boolean senhaValida = this.verificarSenha(compativeis.getCodSistema().toString());
			
			if(senhaValida) {
				return ResponseEntity.ok().body(false);
			} 
				
			return ResponseEntity.ok().body(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	private boolean verificarSenha(String idsis) {
		if(SenhaDoSistemaValida(idsis)){			
			return true;
		}
		return false;
	}
	
	private boolean SenhaDoSistemaValida(String idsis) {
		 //SOUZA 10/07/2012
		boolean resultado = true;	
				
		//BUSCAR A SENHA NA BASE DE DADOS PARA O SISTEMA EM QUESTAO
		String senha = null;	
		try {
			senha = compativeisService.findByCodSistema(Integer.valueOf(idsis))
					.getSenhaDeLiberacao();
		} catch(Exception e){e.printStackTrace();}	
		
		if(senha == null) {
			senha="9X9X2X000481X3X9X1XX";
		}
		
		resultado  = MB_SenhaValida(senha, "MBS"+idsis);
		
		return resultado;
	}
	
	@PostMapping("/validasenhaliberacao")
	public ResponseEntity<?> validaSenha(@RequestBody String senha) {
		try {
			Integer codSistema = compativeisService.findAll().get(0).getCodSistema();
			atualizaSenha(codSistema.toString(), senha);
			return ResponseEntity.ok().body("");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	private void atualizaSenha(String idSistema, String senhaDeLiberacao) {
		// ATUALIZAR A SENHA NA BASE DE DADOS E LIBERAR O SISTEMA
		Compativeis compativeisAux = null;
		try {
			compativeisAux = compativeisService.findByCodSistema(Integer.valueOf(idSistema));
			if (!senhaDeLiberacao.equals("")) {
				compativeisAux.setSenhaDeLiberacao(senhaDeLiberacao);
				compativeisService.atualiza(compativeisAux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	private boolean MB_SenhaValida(String PassWord, String NSis) {
		// SOUZA 10/07/2012
		boolean resultado = true;
		boolean msgdoprogramador = false;
		Date DataV, DataI;
		Date agora = new Date();
		String IDCli, IDSis;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		// VERIFICAR A VALIDADE DA SENHA
		// VERIFICAR SE É POSSIVEL DECODIFICAR A SENHA INFORMADA

		DataV = GetDV(PassWord);
		try {
			if (sdf1.parse("01/01/1950") == DataV) {
				if (msgdoprogramador)
					System.out.println("Erro: Validando Data de Vencimento...");
				return false;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		DataI = GetDI(PassWord);
		try {
			if (sdf1.parse("01/01/9999") == DataI) {
				if (msgdoprogramador)
					System.out.println("Erro: Validando Data Inicial...");
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		IDCli = GetIdCli(PassWord);
		if ("XXX" == IDCli) {
			if (msgdoprogramador)
				System.out.println("Erro: Validando Id do Cliente...");
			return false;
		}

		IDSis = GetIdSis(PassWord);
		if ("XXX00" == IDSis) {
			if (msgdoprogramador)
				System.out.println("Erro: Validando Id do Sistema...");
			return false;
		}

		// CONFERIR DATA DA SENHA COM DATA DO COMPUTADOR
		if (agora.before(DataI) || agora.after(DataV)) {
			if (msgdoprogramador)
				System.out.println("Erro: Comparando a data atual com o período da senha...");
			return false;
		}

		// CONFERIR SISTEMA
		if (!NSis.equals(IDSis)) {
			if (msgdoprogramador)
				System.out.println("Erro: Comparando o código do sistema com o código da senha...");
			return false;
		}

		// CONFERIR CLIENTE
		if (!parametrosService.findAll().get(0).getAcod().equals(IDCli)) {
			if (msgdoprogramador)
				System.out.println("Erro: Comparando o código do cliente com o código da senha...");
			return false;
		}

		return resultado;
	}
		 
		
		
	private Date GetDV(String PassWord) {
	 //SOUZA 06/07/2012
		Date resultado = null;
		SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy");
		 
		Integer DiaV,MesV,AnoV;
		int LetCod;
		char[] MesFull = {'X','X','X'};
	    String MesFullTemp;
	    String SenhaCod;
	     
        try {
			resultado=sdf1.parse("01/01/1950");
		} catch (ParseException e1) {			
			e1.printStackTrace();
		}  
        
		try {
			// DECODIFICAR A SENHA
			SenhaCod = PassWord;

			// ACHAR O DIA DE VENCIMENTO
			DiaV = Integer.parseInt(SenhaCod.substring(0, 1), 10) + Integer.parseInt(SenhaCod.substring(8, 9), 10) * 10;

			// ACHAR O MÊS DE VENCIMENTO
			// PRIMEIRA LETRA DO MES
			LetCod = Integer.valueOf(SenhaCod.charAt(1)) - (DiaV % 10);
			if (LetCod < 65)
				LetCod = LetCod + 26;

			MesFull[0] = (char) LetCod;

			// SEGUNDA LETRA DO MES
			LetCod = Integer.valueOf(SenhaCod.charAt(3)) - (DiaV % 10);
			if (LetCod < 65)
				LetCod = LetCod + 26;

			MesFull[1] = (char) LetCod;

			// TERCEIRA LETRA DO MES
			LetCod = Integer.valueOf(SenhaCod.charAt(5)) - (DiaV % 10);
			if (LetCod < 65)
				LetCod = LetCod + 26;

			MesFull[2] = (char) LetCod;

			MesFullTemp = String.valueOf(MesFull);

			MesV = 0;
			if (MesFullTemp.equals("JAN"))
				MesV = 1;
			if (MesFullTemp.equals("FEV"))
				MesV = 2;
			if (MesFullTemp.equals("MAR"))
				MesV = 3;
			if (MesFullTemp.equals("ABR"))
				MesV = 4;
			if (MesFullTemp.equals("MAI"))
				MesV = 5;
			if (MesFullTemp.equals("JUN"))
				MesV = 6;
			if (MesFullTemp.equals("JUL"))
				MesV = 7;
			if (MesFullTemp.equals("AGO"))
				MesV = 8;
			if (MesFullTemp.equals("SET"))
				MesV = 9;
			if (MesFullTemp.equals("OUT"))
				MesV = 10;
			if (MesFullTemp.equals("NOV"))
				MesV = 11;
			if (MesFullTemp.equals("DEZ"))
				MesV = 12;
			if (MesV == 0) {
				return resultado;
			}

			// ACHAR O ANO DE VENCIMENTO
			AnoV = 2000 + (Integer.parseInt(SenhaCod.substring(7, 8), 10) * 100
					+ Integer.parseInt(SenhaCod.substring(4, 5), 10) * 10
					+ Integer.parseInt(SenhaCod.substring(2, 3), 10)) - DiaV - MesV;

			resultado = sdf1.parse(String.valueOf(DiaV) + "/" + String.valueOf(MesV) + "/" + String.valueOf(AnoV));

		} catch (Exception e) {
			return resultado;
		}

		return resultado;        

	}
			 
			 @SuppressWarnings("deprecation")
	private Date GetDI(String PassWord) {

		Date resultado = null;
		String SenhaCod;
		Integer Validade, DiaV;
		Date DataV;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		try {
			resultado = sdf1.parse("01/01/9999");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		try {
			SenhaCod = PassWord;
			DataV = GetDV(PassWord);

			DiaV = DataV.getDate();

			if (DiaV % 10 > 5)
				Validade = Integer.parseInt(SenhaCod.substring(9, 10), 10) * 10
						+ Integer.parseInt(SenhaCod.substring(6, 7), 10);
			else
				Validade = Integer.parseInt(SenhaCod.substring(6, 7), 10) * 10
						+ Integer.parseInt(SenhaCod.substring(9, 10), 10);

			Calendar agora = Calendar.getInstance();
			agora.setTime(DataV);
			agora.add(Calendar.DAY_OF_YEAR, -Validade);
			resultado = agora.getTime();
		} catch (Exception e) {
			return resultado;
		}
		return resultado;
	}
			 
	private String GetIdCli(String PassWord) {

		String resultado;
		String SenhaCod;
		Integer DigC;
		int LetCod;
		char[] CliFull = { 'X', 'X', 'X' };

		resultado = "XXX";
		
		try {
			SenhaCod = PassWord;

			DigC = Integer.parseInt(SenhaCod.substring(10, 11), 10);

			// PRIMEIRO DIGITO DO IDCLI
			LetCod = Integer.valueOf(SenhaCod.charAt(19)) - DigC;
			if (LetCod < 65)
				LetCod = LetCod + 26;
			CliFull[0] = (char) LetCod;

			// SEGUNDO DIGITO DO IDCLI
			LetCod = Integer.valueOf(SenhaCod.charAt(12)) - DigC;
			if (LetCod < 65)
				LetCod = LetCod + 26;
			CliFull[1] = (char) LetCod;

			// TERCEIRO DIGITO DO IDCLI
			LetCod = Integer.valueOf(SenhaCod.charAt(15)) - DigC;
			if (LetCod < 48)
				LetCod = LetCod + 10;
			CliFull[2] = (char) LetCod;

			resultado = String.valueOf(CliFull);

		} catch (Exception e) {
			return resultado;
		}
		return resultado;
	}

	private String GetIdSis(String PassWord) {

		String resultado;
		String SenhaCod;
		Integer DigS;
		int LetCod;
		char[] SisFull = { 'X', 'X', 'X', '0', '0' };

		resultado = "XXX00";

		try {
			SenhaCod = PassWord;
			DigS = Integer.parseInt(SenhaCod.substring(11, 12), 10);

			// PRIMEIRO DIGITO DO IDSIS
			LetCod = Integer.valueOf(SenhaCod.charAt(14)) - DigS;
			if (LetCod < 65)
				LetCod = LetCod + 26;
			SisFull[0] = (char) LetCod;

			// SEGUNDO DIGITO DO IDSIS
			LetCod = Integer.valueOf(SenhaCod.charAt(16)) - DigS;
			if (LetCod < 65)
				LetCod = LetCod + 26;
			SisFull[1] = (char) LetCod;

			// TERCEIRO DIGITO DO IDSIS
			LetCod = Integer.valueOf(SenhaCod.charAt(18)) - DigS;
			if (LetCod < 65)
				LetCod = LetCod + 26;
			SisFull[2] = (char) LetCod;

			// QUARTO DIGITO DO IDSIS
			LetCod = Integer.valueOf(SenhaCod.charAt(17)) - DigS;
			if (LetCod < 48)
				LetCod = LetCod + 10;
			SisFull[3] = (char) LetCod;

			// QUINTO DIGITO DO IDSIS
			LetCod = Integer.valueOf(SenhaCod.charAt(13)) - DigS;
			if (LetCod < 48)
				LetCod = LetCod + 10;
			SisFull[4] = (char) LetCod;

			resultado = String.valueOf(SisFull);
		} catch (Exception e) {
			return resultado;
		}
		return resultado;
	}

}
