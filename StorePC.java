package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class StorePC {
    public static void main(String[] args) {
     
        // Dati da inserire nella tabella
        ArrayList <pc> listaPc = new ArrayList<>();
        
        int scelta = 0;
        
        do {
        	
        	
        	
        	
        Scanner input = new Scanner (System.in);
        System.out.println("BENVENUTO NELLO STORE DI PC!");
        System.out.println("Menu:"
        		+ "\n1 per visualizzare tutti i pc"
        		+ "\n2 per richiedere informazioni su un pc"
   				+ "\n0 per terminare"
   				+ "\nCosa vuoi fare? ");
        
        scelta = input.nextInt();
        
        switch (scelta) {
        
        	case 1:
        		//Visualizza tutte le auto
		    	String selectQuery = "SELECT * FROM StorePC";
		    	
		    	listaPc.clear();
		    	
		        // Tentativo di connessione al database e lettura dei dati dalla tabella
		        try (Connection conn = DriverManager.getConnection(ConnMySQL.getUrl(),ConnMySQL.getUser(),ConnMySQL.getPwd());
		              Statement stmt = conn.createStatement();
		              ResultSet rs = stmt.executeQuery(selectQuery)) {
		
		            // Iterazione sui risultati e lettura dei dati
		            while (rs.next()) {
		                 int id = rs.getInt("id");
		                 String nome = rs.getString("nome");
		                 String marca = rs.getString("marca");
		                 String tipo = rs.getString("tipologia");
		                 double prezzo = rs.getDouble("prezzo");
		                 int quantita = rs.getInt("quantita");
		                 
		                 // Creazione di un nuovo oggetto Prodotto e aggiunta alla lista
		                 pc PC = new pc (id, nome, marca, tipo, prezzo, quantita);
		                 listaPc.add(PC);
		            }
		
		            // Stampa dei prodotti nella lista
		            for (pc p : listaPc) {
		                 System.out.println(p);
		            }
		
		        } catch (SQLException e) {
		             // Gestione delle eccezioni per la connessione al database o la lettura dei dati
		             System.out.println("Errore durante la lettura dei dati dalla tabella 'auto'");
		             e.printStackTrace();
		        }
		        break;
        
        	case 2:
        		//Richiesta informazioni via mail
 				System.out.print("Scrivi il nome del pc di cui vuoi informazioni: ");
 				String nomePC2 = input.next();
 				
 				System.out.print("Scrivi la tua mail: ");
 				String mailCliente = input.next();
 				
 				boolean checkInvio = false;
 				
 				for (pc p : listaPc) {
 					if (p.getNome().equals(nomePC2)) {
 						String obj = "Risposta a richiesta informazioni";
 						String body = "Ciao,\necco le informazioni che ci hai chiesto:\n" + p.toString() + "\nBuona giornata!"; 
 						SendEmail.invia(mailCliente, obj, body);
 						checkInvio = true;
 					}	
 				}
 				
 				if (!checkInvio) {
 						System.out.println("PC non disponibile in archivio.");
 				}
        		break;
        		            
			default:
				 System.out.println("Scelta non riconosciuta, riprova!");
				 break;
			 	
        	}
        	
        System.out.println("\n------------------------------------\n");
        
        } while (scelta != 0);
        
        System.out.println("\nCIAO ALLA PROSSIMA!\n");
  
    }
 }