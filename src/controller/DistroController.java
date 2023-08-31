package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class DistroController {

	public DistroController() {
		super();
	}
	
	private String os(){
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void exibeDistro() {
		String os = os();
		if (os.contains("Windows")) {
			JOptionPane.showMessageDialog(null, "SO inválido (Windows)");
		}
		else if(os.contains("Linux")) {
			String process = "cat /etc/os-release";
			Process Distro;
			try {
				Distro = Runtime.getRuntime().exec(process);
				InputStream fluxo = Distro.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha!=null) {
					if (linha.contains("PRETTY_NAME=")) {
						String l[] = linha.split("\"");
						System.out.println("Nome: " + l[1]);
					} else if (linha.contains("VERSION=")) {
						String l[] = linha.split("\"");
						System.out.println("Versão: " + l[1]);
					}
					linha = buffer.readLine();
					}
				buffer.close();
				leitor.close();
				fluxo.close();
			}catch (Exception e) {
				e.printStackTrace();
				System.err.println(e);
			}
			
		}
	}

}
