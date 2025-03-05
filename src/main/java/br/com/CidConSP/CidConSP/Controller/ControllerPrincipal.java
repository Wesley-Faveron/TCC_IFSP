package br.com.CidConSP.CidConSP.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerPrincipal
{

    @GetMapping("/")
    public String redirecionarParaLogin()
    {
        return "redirect:/usuarios/login";
    }
    
    @GetMapping("/sobre")
    public String exibirPaginaSobre()
    {
        return "sobre";
    }
}