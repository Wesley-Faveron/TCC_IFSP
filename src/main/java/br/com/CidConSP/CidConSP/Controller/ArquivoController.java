package br.com.CidConSP.CidConSP.Controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController
{

    private final String UPLOAD_DIR = "uploads";

    @GetMapping("/{nomeArquivo:.+}")
    public ResponseEntity<Resource> servirArquivo(@PathVariable String nomeArquivo)
    {
        try
        {
            Path caminhoArquivo = Paths.get(UPLOAD_DIR).resolve(nomeArquivo).normalize();
            Resource recurso = new UrlResource(caminhoArquivo.toUri());

            if (!recurso.exists() || !recurso.isReadable())
            {
                throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo);
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Ajuste o tipo conforme necessário
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                    .body(recurso);

        }
        catch (Exception e)
        {
            throw new RuntimeException("Erro ao carregar o arquivo: " + nomeArquivo, e);
        }
    }
}
