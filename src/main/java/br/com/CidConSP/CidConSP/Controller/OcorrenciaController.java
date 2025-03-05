package br.com.CidConSP.CidConSP.Controller;

import br.com.CidConSP.CidConSP.Entity.Categoria;
import br.com.CidConSP.CidConSP.Entity.Midia;
import br.com.CidConSP.CidConSP.Entity.Ocorrencia;
import br.com.CidConSP.CidConSP.Entity.OrgaoPublico;
import br.com.CidConSP.CidConSP.Entity.Usuario;
import br.com.CidConSP.CidConSP.Service.CategoriaService;
import br.com.CidConSP.CidConSP.Service.MidiaService;
import br.com.CidConSP.CidConSP.Service.OcorrenciaSender;
import br.com.CidConSP.CidConSP.Service.OcorrenciaService;
import br.com.CidConSP.CidConSP.Service.StatusOcorrenciaService;
import br.com.CidConSP.CidConSP.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ocorrencias")
public class OcorrenciaController
{

    @Autowired
    private OcorrenciaService ocorrenciaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private StatusOcorrenciaService statusOcorrenciaService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private MidiaService midiaService;
    
    @Autowired
    private OcorrenciaSender ocorrenciaSender;

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model)
    {
        model.addAttribute("ocorrencia", new Ocorrencia());
        model.addAttribute("categorias", categoriaService.listarCategorias()); // Lista categorias
        return "CadastroOcorrencia";
    }

    @PostMapping("/cadastro")
    public String cadastrarOcorrencia(
            @ModelAttribute Ocorrencia ocorrencia,
            @RequestParam("arquivo") MultipartFile[] arquivos,
            HttpSession session,
            RedirectAttributes redirectAttributes)
    {
        try
        {
            Integer idUsuario = (Integer) session.getAttribute("idUsuario");
            Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            Categoria categoria = categoriaService.buscarPorId(ocorrencia.getCategoria().getId());
            OrgaoPublico orgaoPublico = categoria.getOrgaoPublico();

            ocorrencia.setUsuario(usuario);
            ocorrencia.setCategoria(categoria);
            ocorrencia.setOrgaoPublico(orgaoPublico);
            ocorrencia.setStatus(statusOcorrenciaService.buscarPorDescricao("enviado"));
            ocorrencia.setDataCriacao(LocalDateTime.now());

            // Salva a ocorrência
            Ocorrencia ocorrenciaSalva = ocorrenciaService.salvarOcorrencia(ocorrencia);

            // Processa os arquivos enviados
            for (MultipartFile arquivo : arquivos)
            {
                if (!arquivo.isEmpty())
                {
                    String nomeArquivo = salvarArquivo(arquivo);
                    String tipo = arquivo.getContentType();

                    Midia midia = new Midia();
                    midia.setCaminhoArquivo(nomeArquivo);
                    midia.setTipo(tipo);
                    midia.setOcorrencia(ocorrenciaSalva);
                    midia.setDataUpload(LocalDateTime.now());

                    midiaService.salvarMidia(midia);
                }
            }
            
            ResponseEntity<String> response = ocorrenciaSender.enviar(ocorrencia);
            
            // Extraindo o protocolo da resposta JSON
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.getBody());
            String protocoloRecebido = jsonResponse.get("protocolo").asText();

            // Atualiza a ocorrência com o protocolo recebido
            ocorrenciaSalva.setProtocolo(protocoloRecebido);
            ocorrenciaService.salvarOcorrencia(ocorrenciaSalva);


            redirectAttributes.addFlashAttribute("flashMessage", "Ocorrência cadastrada com sucesso!");
            
            return "redirect:/ocorrencias/index";
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("flashMessage", "Erro ao cadastrar a ocorrência: " + e.getMessage());
            
            return "redirect:/ocorrencias/cadastro";
        }
    }

    
    @GetMapping("/index")
    public String exibirOcorrenciasUsuario(Model model, HttpSession session)
    {
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (idUsuario != null)
        {
            List<Ocorrencia> ocorrencias = ocorrenciaService.buscarPorUsuarioId(idUsuario);
            model.addAttribute("ocorrencias", ocorrencias);
            
            return "index";
        }

        return "redirect:/usuarios/login";
    }


    @GetMapping("/sobre")
    public String exibirPaginaSobre()
    {
        return "redirect:/sobre";
    }
    
    private String salvarArquivo(MultipartFile arquivo) throws IOException
    {
        // Diretório onde os arquivos serão salvos
        String diretorioUpload = "uploads/";

        // Cria o diretório, caso não exista
        Path diretorioPath = Paths.get(diretorioUpload);
        if (!Files.exists(diretorioPath))
        {
            Files.createDirectories(diretorioPath);
        }

        // Gera um nome único para o arquivo
        String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
        Path caminhoArquivo = diretorioPath.resolve(nomeArquivo);

        // Salva o arquivo no diretório
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        // Retorna o nome do arquivo salvo
        return nomeArquivo;
    }

    
    @GetMapping("/detalhes/{id}")
    public String exibirDetalhesOcorrencia(@PathVariable Integer id, Model model)
    {
        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada com ID: " + id));

        // Garante que a lista de mídias está inicializada
        ocorrencia.getMidia().size();

        model.addAttribute("ocorrencia", ocorrencia);
        return "DetalhesOcorrencia";
    }


}