package br.com.CidConSP.CidConSP.Service;

import br.com.CidConSP.CidConSP.Entity.Midia;
import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import br.com.CidConSP.CidConSP.Entity.OrgaoPublico;
import br.com.CidConSP.CidConSP.Entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.List;

@Service
public class OcorrenciaSender
{

    private final MidiaService midiaService;
    private final OrgaoPublicoService orgaoPublicoService;
    private static final String PADRAO_IMAGE_PATH = "uploads/padrao.png";

    @Autowired
    public OcorrenciaSender(MidiaService midiaService, OrgaoPublicoService orgaoPublicoService)
    {
        this.midiaService        = midiaService;
        this.orgaoPublicoService = orgaoPublicoService;
    }

    public ResponseEntity<String> enviar(Ocorrencia ocorrencia)
    {
        HttpHeaders  headers               = new HttpHeaders();
        RestTemplate restTemplate          = new RestTemplate();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        
        List<Midia>  midias       = ocorrencia.getMidia();
        Usuario      usuario      = ocorrencia.getUsuario();
        OrgaoPublico orgaoPublico = ocorrencia.getCategoria().getOrgaoPublico();        
        String       apiEndpoint  = orgaoPublico.getApiEndpoint();
        
        boolean midiaAdicionada = false;
        
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        
        if (usuario == null)
        {
            throw new RuntimeException("Usuário não encontrado na ocorrência.");
        }

        if (ocorrencia.getCategoria() == null)
        {
            throw new RuntimeException("Categoria não encontrada na ocorrência.");
        }
        
        if (apiEndpoint == null || apiEndpoint.isEmpty())
        {
            throw new RuntimeException("API Endpoint do órgão público não configurado.");
        }
        
        if (midias == null || midias.isEmpty())
        {
            midias = midiaService.buscarPorOcorrenciaId(ocorrencia.getId());
            ocorrencia.setMidia(midias);
        }

        body.add("nome", usuario.getNome());
        body.add("cpf", usuario.getCpf());
        body.add("descricao", ocorrencia.getDescricao());        

        if (midias != null && !midias.isEmpty())
        {
            for (Midia midia : midias)
            {
                File file = new File("uploads/" + midia.getCaminhoArquivo());
                
                if (file.exists())
                {
                    body.add("files", new FileSystemResource(file));
                    
                    midiaAdicionada = true;
                }
                else
                {
                    System.out.println("Arquivo não encontrado: " + file.getAbsolutePath());
                }
            }
        }

        if (!midiaAdicionada)
        {
            File placeholderFile = obterImagemPlaceholder();
            
            body.add("files", new FileSystemResource(placeholderFile));
        }
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(apiEndpoint, HttpMethod.POST, requestEntity, String.class);
    }

    private File obterImagemPlaceholder()
    {
        File file = new File(PADRAO_IMAGE_PATH);
        
        if (!file.exists())
        {
            throw new RuntimeException("Arquivo de imagem padrao não encontrado: " + PADRAO_IMAGE_PATH);
        }
        
        return file;
    }
}