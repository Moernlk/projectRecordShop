package br.com.doit.recordshop.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import br.com.doit.recordshop.model.RecordLabel;
import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXS;

/*
  Listagem de gravadoras, com cadastro em modal.
 
  O modal não é um componente nem um framework: é um bloco de HTML que só
  é desenhado quando mostrandoForm está true. O visual de janela sobreposta
  vem do CSS do MainLayout.
 */


public class RecordLabelListPage extends ERXComponent {

    private static final long serialVersionUID = 1L;

    /** Recebe a gravadora da vez a cada volta do WORepetition. */
    public RecordLabel gravadoraAtual;

    private NSArray<RecordLabel> gravadoras;

    /** O objeto que o modal está cadastrando. */
    private RecordLabel novaGravadora;

    
    /*	O objeto que o modal está deletando	29-out-26*/ 

    /** Se o modal está aberto. É o WOConditional do HTML que lê isto. */
    private boolean mostrandoForm;

    private String erro;

    public RecordLabelListPage(WOContext context) {
        super(context);
    }

    /** O contexto compartilhado da sessão, usado só para LER. */
    private EOEditingContext ec() {
        return session().defaultEditingContext();
    }

    // ======================================================================= LISTAGEM

    /**
     * O resultado fica em cache no campo. Sem isso, cada volta da
     * repetition dispararia uma consulta nova.
     */
    public NSArray<RecordLabel> gravadoras() {
        if (gravadoras == null) {
            gravadoras = RecordLabel.fetchAllRecordLabels(
                    ec(), ERXS.ascs(RecordLabel.NAME_KEY));
        }
        return gravadoras;
    }

    public boolean listaVazia() {
        return gravadoras().isEmpty() || gravadoras() == null;
    }
    


    /**
     * Atravessa o relacionamento para-muitos, uma vez por linha.
     *
     * A tabela record_label não tem coluna apontando para os artistas:
     * esta contagem vem do caminho inverso, consultando artist pela FK.
     */
//    public int qtdArtistasDaAtual() {
//       return gravadoraAtual.artists().count();
//    }
    
    
    
    public WOActionResults deletar() {    	
    	try {
				/*
				linkar qual linha deve ser deletada
				provavelmente pelo atributo gravadora
				solicitar delete ao banco
	    		*/
    			ec().deleteObject(gravadoraAtual);
    			ec().saveChanges();		
				gravadoras = null;
    		
		} catch (Exception e) {
				ec().revert();
				gravadoras = null;
				erro = e.getMessage();
			
		}
        return null;
    }
    public WOActionResults editar() {
    	EOEditingContext ecEdicao = ERXEC.newEditingContext();
    	novaGravadora = gravadoraAtual.localInstanceIn(ecEdicao);
    	
    	erro = null;
    	mostrandoForm = true;
    	
    	return null;
    }


    // ========================================================================== MODAL

    public boolean mostrandoForm() {
        return mostrandoForm;
    }

    public RecordLabel novaGravadora() {
        return novaGravadora;
    }

    /**
     * Abre o modal.
     *
     * Cria a gravadora em branco num editing context NOVO — não no da
     * sessão. Assim, se o usuário cancelar, o objeto pela metade vai
     * embora junto com o contexto.
     */
    public WOActionResults abrirForm() {
        EOEditingContext ecNovo = ERXEC.newEditingContext();

        novaGravadora = ERXEOControlUtilities.createAndInsertObject(
                ecNovo, RecordLabel.class);

        erro          = null;
        mostrandoForm = true;

        return null;
    }

    /**
     * Grava e fecha.
     *
     * O saveChanges() é onde o validateForSave() da entidade roda. Sem
     * o try/catch, a exceção viraria a página de erro genérica e o
     * usuário perderia o que digitou.
     */
    public WOActionResults salvar() {
        try {
            novaGravadora.editingContext().saveChanges();

            gravadoras    = null;
            mostrandoForm = false;
            return null;

        } catch (Exception e) {
        	
            erro = e.getMessage();
            return null;
        }
    }
    

    /**
     * Descarta e fecha.
     *
     * O revert() é obrigatório: o objeto entrou no contexto na criação,
     * antes de o usuário digitar qualquer coisa.
     */
    public WOActionResults cancelar() {
        if (novaGravadora != null) {
            novaGravadora.editingContext().revert();
        }

        novaGravadora = null;
        mostrandoForm = false;
        erro          = null;
        ec().revert();
        return null;
    }

    // =========================================================================== ERRO

    public boolean temErro() {
        return erro != null;
    }

    public String erro() {
        return erro;
    }
    
}

    