package br.com.doit.recordshop.components;

import com.webobjects.appserver.WOContext;


//import br.com.doit.recordshop.model.Album;
//import br.com.doit.recordshop.model.Artist;
import er.extensions.components.ERXComponent;

import com.webobjects.appserver.WOActionResults;

public class MainLayout extends ERXComponent {
    private static final long serialVersionUID = 1L;

	public MainLayout(WOContext context) {
        super(context);
    }
	
	public WOActionResults abrirGravadoras() {
		return pageWithName(RecordLabelListPage.class);
	}
	public WOActionResults abrirArtistas() {
		return pageWithName(ArtistListPage.class);
	}


	public WOActionResults abrirAlbuns() {
		return pageWithName(AlbumListPage.class);
	}
	
	
	
	
	
}