
package br.com.doit.recordshop.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

import br.com.doit.recordshop.model.Album;
import br.com.doit.recordshop.model.Artist;
import br.com.doit.recordshop.model.Genre;
import br.com.doit.recordshop.model.RecordLabel;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.joda.time.DateTime;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;
import com.webobjects.appserver.WOActionResults;

public class ArtistListPage extends ERXComponent {
	
    private static final long serialVersionUID = 1L;


	public ArtistListPage(WOContext context) {
		super(context);}
    
	public NSTimestamp birthdayString;
	
	private NSArray<RecordLabel> gravadoras;
    public RecordLabel atualGravadora;
	public RecordLabel optGravadora;
    
    private NSArray<Genre> genres;
    
	public Genre atualGenre;
	public Genre atualSecGenre;
	
    private NSArray<Artist> artists;
    
    private Artist novoArtist;
    
    public Artist novoArtist() {
    	return novoArtist;
    }
    
    public Artist atualArtist;
    
    private String erro;
    
	private boolean mostrandoForm;

	
	
    private EOEditingContext ec() {
    	return session().defaultEditingContext();
    }
    
    

    
    public Artist novoArtista() {
    	return novoArtist;
    }
    
    
 // LISTAGEM   
    
    
    
    public NSArray<Artist> artists(){
    	if (artists == null || artists.isEmpty()) {
    		artists = Artist.fetchAllArtists(
    				ec(), ERXS.ascs(Artist.NAME_KEY));
    		
    	}
    	return artists;
    }
    
    public boolean listaVazia() {
    	return artists() == null || artists().isEmpty() ;
    }
    public WOActionResults deletar() {
    	try {
			/*
			linkar qual linha deve ser deletada
			provavelmente pelo atributo gravadora
			solicitar delete ao banco
    		*/
			//Album albunsArtist
			ec().deleteObject(atualArtist);
			ec().saveChanges();		
			artists = null;
		
	} catch (Exception e) {
			ec().revert();
			artists = null;
			erro = e.getMessage();
		
	}
    return null;
    }
    public WOActionResults editar() {
    	EOEditingContext ecEdicao = ERXEC.newEditingContext();
    	novoArtist = atualArtist.localInstanceIn(ecEdicao);
    	
    	erro = null;
    	mostrandoForm = true;
    	
    	return null;    	
    }
    
    
    
//  MODAL    


    
    public boolean mostrandoForm() {
    	return mostrandoForm;
    } 
    public WOActionResults abrirForm() {
    	EOEditingContext ecNovo = ERXEC.newEditingContext();
    	novoArtist = ERXEOControlUtilities.createAndInsertObject(ecNovo, Artist.class);
    	atualGravadora = null;
    	
    	erro = null;
    	mostrandoForm = true;
    	
    	return null;
    }
    
    
    
    private EOEnterpriseObject recordLabelArtist;
    public EOEnterpriseObject recordLabelArtist() {
    	return recordLabelArtist;
    }
    public NSArray<RecordLabel> gravadoras(){
    	if (gravadoras == null || gravadoras.isEmpty()) {
    		gravadoras = RecordLabel.fetchAllRecordLabels(
    				ec(), ERXS.ascs(RecordLabel.NAME_KEY));
    		
    	}
    	
    	return gravadoras;
    }
    
    
    public NSArray<Genre> genres(){
    	genres = new NSArray<>(Genre.values());
    	return genres;
    }

    
    public WOActionResults salvar() {
    	try {
    		
    		RecordLabel gravadoraLocal = atualGravadora == null
    				? null
    				: RecordLabel.localInstanceIn(novoArtist.editingContext(), atualGravadora);
    		novoArtist.setRecordLabelRelationship(gravadoraLocal);
    		
    		
//			System.out.println("birth: "+ novoArtist.birthday());
//			System.out.println("birthString: "+birthdayString); 		
//    		System.out.println("genre: " + novoArtist.mainGenre());
//    		System.out.println("genreSEC: " + novoArtist.secondaryGenre());		
//    		System.out.println("RL: " + atualGravadora);
//    		System.out.println("RLr: " + novoArtist.recordLabel());
//    		System.out.println(novoArtist.country());
    		
			novoArtist.editingContext().saveChanges();
			
			
    		artists = null;
    		atualGravadora = null;
        	novoArtist = null;
        	mostrandoForm = false;
        	birthdayString = null;
        	atualGenre = null;
        	atualSecGenre = null;
        	erro = null;    		
			mostrandoForm = false;
			
		} catch (Exception e) {
			erro = e.getMessage();
			System.out.print(e.getMessage());
		}
    	return null;
    }
    public WOActionResults cancelar() {
    	if (novoArtist != null) {
    		novoArtist.editingContext().revert();
    	}
    	novoArtist = null;
    	mostrandoForm = false;
    	birthdayString = null;
    	atualGenre = null;
    	atualSecGenre = null;
    	erro = null;
    	ec().revert();
    	return null; 
    }
    
    
//  ERRO    
    public boolean temErro() {
    	return erro != null;
    }
    
    public String erro() {
    	return erro;
    }
  
}