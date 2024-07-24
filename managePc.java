package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class managePc {
    public static void main(String[] args) {
     
        // Dati da inserire nella tabella
        ArrayList <pc> listaPc = new ArrayList<>();
        ArrayList <pc> listaPrezzo = new ArrayList<>();
        
        int scelta = 0;
        
        do {
        Scanner input = new Scanner (System.in);
        System.out.println("BENVENUTO NEL PC-MANAGER!");
        System.out.println("Menu:"
        		+ "\n1 per aggiungere un pc"
        		+ "\n2 per aggiornare la quantità di un pc"
   				+ "\n3 per ricercare un pc"
   				+ "\n0 per terminare"
   				+ "\nCosa vuoi fare? ");
        
        scelta = input.nextInt();
        
        switch (scelta) {
        
        	case 1:
        		//scelta 1 - inserisci pc
		        System.out.print("Inserisci il nome: ");
		        String nomePC = input.next();
		        
		        System.out.print("Inserisci la marca: ");
		        String marcaPC = input.next();
		        
		        System.out.print("Inserisci la tipologia: ");
		        String tipoPC = input.next();
		        
		        System.out.print("Inserisci il prezzo: ");
		        double prezzoPC = input.nextDouble();
		        
		        System.out.print("Inserisci la quantità: ");
		        int quantitaPC = input.nextInt();
		        
		        String insertQuery = "INSERT INTO StorePC (nome, marca, tipologia, prezzo, quantita) VALUES (?, ?, ?, ?, ?)";
		
		        
		        				
		        // Tentativo di connessione al database e inserimento dei dati nella tabella auto
		        try (Connection conn = DriverManager.getConnection(ConnMySQL.getUrl(),ConnMySQL.getUser(),ConnMySQL.getPwd());
		        		PreparedStatement stmt = conn.prepareStatement(insertQuery)){
				        	stmt.setString(1, nomePC);
				        	stmt.setString(2, marcaPC);
				        	stmt.setString(3, tipoPC);
				        	stmt.setDouble(4, prezzoPC);
				        	stmt.setInt(5, quantitaPC);
				               	
			            // Esecuzione della query per l'inserimento dei dati
			            int rowsAffected = stmt.executeUpdate();
		
			            // Stampa il numero di righe aggiornate
			            System.out.println("\nNumero di righe aggiornate: " + rowsAffected);
		
		        } catch (SQLException e) {
		            // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
		            System.out.println("Errore durante l'inserimento dei dati nella tabella 'StorePC'");
		            e.printStackTrace();
		        }
		        
		        break;
        
        	case 2:
        		//scelta 2 - aggiorna quantità pc
        		
				// chiediamo all'utente di inserire il nome del pc
				System.out.print("Inserisci il nome del pc: ");
				String nomePC2 = input.next();
				System.out.print("Inserisci nuova quantità: ");
				int qnt = input.nextInt();
				
	    	 
				String query2 = "UPDATE StorePC  SET quantita = (?) WHERE nome= (?) ";
				try (Connection conn = DriverManager.getConnection(ConnMySQL.getUrl(),ConnMySQL.getUser(),ConnMySQL.getPwd());
	    		    PreparedStatement stmt = conn.prepareStatement(query2))
	    			
	    			{   // andiamo a definire il segnaposto nello statement
	    				stmt.setInt(1, qnt);
	    				stmt.setString(2, nomePC2);
	    				int rows = stmt.executeUpdate(); //ritorna un intero col numero di righe cambiate nella tabella
	   				 	if (rows > 0) {
	   					 //se vengono trovati dati, vengono stampati
	   				 		System.out.println("Quantità " + nomePC2 + " aggiornata con successo!");  			
	   				 	}else {
	   				 		System.out.println("Pc non trovato nel database!"); 
	   				 	}
    			    	
	    			} catch (SQLException e) {
		    	     // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
		    	     System.out.println("Errore durante la ricerca dei dati nella tabella 'StorePC'");
		    	     e.printStackTrace();
				}  
				break;
        		
        	case 3:
        		//scelta 3 - cerca per prezzo
        		
        		//pulisco la lista da eventuali ricerche precedenti
        		listaPrezzo.clear();
    	 
        		String query1 = "SELECT * FROM StorePC WHERE prezzo = (?)";
				// andiamo a ricercare un pc all'interno del database
				System.out.println("Inserisci il prezzo del pc da ricercare: ");
				// chiediamo all'utente di inserire il nome del pc
				double prezzoI = input.nextDouble();
				// creiamo una connessione e andiamo a dichiarare uno statement di tipo prepared
				try (Connection conn = DriverManager.getConnection(ConnMySQL.getUrl(),ConnMySQL.getUser(),ConnMySQL.getPwd());
			    PreparedStatement stmt = conn.prepareStatement(query1))
				
				{   // andiamo a definire il segnaposto nello statement
					stmt.setDouble(1, prezzoI);
					// andaimo ad assegnare il risultato della query a un result set
					ResultSet rs = stmt.executeQuery(); //ritorna un result set, struttura che ci torna i risultati della query
					 while (rs.next()) {
						 // se vengono tovati dati, vengono stampati
			                int id = rs.getInt("id");
			                String nome = rs.getString("nome");
			                String marca = rs.getString("marca");
			                String tipo = rs.getString("tipologia");
			                double prezzo = rs.getDouble("prezzo");
			                int quantita = rs.getInt("quantita");
			                pc PC = new pc (id, nome, marca, tipo, prezzo, quantita);
			                listaPrezzo.add(PC);
					 }
			
				}catch (SQLException e) {
				     // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
				     System.out.println("Errore durante la ricerca dei dati nella tabella 'StorePC'");
				     e.printStackTrace();
				}  
			
				//stampo i pc nella listaprezzo che corrispondono alla mia ricerca
				for (pc p : listaPrezzo) {
					System.out.println(p);
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
