package com.felipeseiji.encurtador.links;

import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    //Gerar uma url aleatória
    public String gerarUrlAleatoria(){
        return RandomStringUtils.randomAlphanumeric(5,10);
    }

    public Link encurtarUrl(String urlOriginal){
        Link link = new Link();
        link.setUrlLonga(urlOriginal);
        link.setUrlEncurtada(gerarUrlAleatoria());
        link.setCriadaEm(LocalDateTime.now());
        link.setUrlQrCode("Indisponivel");
        return linkRepository.save(link);
    }

    public Link obterUrlOriginal(String urlEncurtada){
        try {
            return linkRepository.findByUrlEncurtada(urlEncurtada);
        } catch (Exception e) {
            throw new RuntimeException("Url não existe");
        }
    }
}
