package br.com.doit.recordshop.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSTimestamp;

import br.com.doit.recordshop.model.Album;
import br.com.doit.recordshop.model.Artist;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXS;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;








public class AlbumListPage extends ERXComponent {
	
    private static final long serialVersionUID = 1L;

	public AlbumListPage(WOContext context) {
        super(context);
    }    
	
	
	public NSTimestamp releaseDateString;

	
	private Album novoAlbum;
	
	public Album atualAlbum;

	private NSArray <Artist> artists;
	public Artist atualArtist;
	public Artist optArtist;
	
	private NSArray<Album> albums;
	
    public boolean mostrandoForm;
	
	
	private String erro;
	
	
	
	public Album novoAlbum() {
		return novoAlbum;
	}

	
	private EOEditingContext ec() {
		return session().defaultEditingContext();
	}
	
	
//	LISTAGEM

	public NSArray <Album> albums() {
		if(albums == null || albums.isEmpty()) {
			albums = Album.fetchAllAlbums(
					ec(), ERXS.ascs(Album.NAME_KEY));
		}
		return albums;
	}	
	public NSArray <Artist> artists() {
		if(artists == null || artists.isEmpty()) {
			artists = Artist.fetchAllArtists(
					ec(), ERXS.ascs(Artist.NAME_KEY));
		}
		return artists;
	}
	
	public boolean listaVazia() {
	    return albums() == null || albums().isEmpty();
	}
    
	public WOActionResults deletar() {
    	try {
    		try {

    			ec().deleteObject(atualAlbum);
    			ec().saveChanges();		
				albums = null;
    		
		} catch (Exception e) {
				ec().revert();
				albums = null;
				erro = e.getMessage();
			
		}
		} catch (Exception e) {
			erro = e.getMessage();
			return null;

		}
		return null;
    }
	public WOActionResults editar() {
    	EOEditingContext ecEdicao = ERXEC.newEditingContext();
    	novoAlbum = atualAlbum.localInstanceIn(ecEdicao);
    	
    	erro = null;
    	mostrandoForm = true;
    	
    	return null;
	}
    
    
    
//  MODAL
    

    
	public WOActionResults abrirForm() {
		EOEditingContext ecNovo = ERXEC.newEditingContext();
		
		novoAlbum = ERXEOControlUtilities.createAndInsertObject(ecNovo, Album.class);
		
		erro = null;
		mostrandoForm = true;
		
		
		return null;
	}
	
	
	
	public WOActionResults salvar() {
		try {
			
			Artist localArtist = atualArtist == null
					? null
					: Artist.localInstanceIn(novoAlbum.editingContext(), atualArtist);
			novoAlbum.setArtistRelationship(localArtist);
			System.out.println(novoAlbum.name());
			System.out.println(novoAlbum.releaseDate());
			System.out.println(novoAlbum.artist());
//			System.out.println(artistLocal);
			
			novoAlbum.editingContext().saveChanges();
			novoAlbum = null;
			albums = null;
			atualAlbum = null;
			localArtist = null;
			optArtist = null;
			
			mostrandoForm = false;
		} catch (Exception e) {
			
			erro = e.getMessage();
		}
		return null;
	}
	
	public WOActionResults cancelar() {
		if (novoAlbum != null) {
			novoAlbum.editingContext().revert();
		}
		novoAlbum = null;
		mostrandoForm = false;
		erro = null;
		ec().revert();
		
		return null;
	}	
	
    
    
    
    
    
    
    
    
//	ERRO
	
	
	public boolean temErro() {
		return erro != null;
	}
	
	public String erro() {
		return erro;
	}
	
	
	
	
	
	
}