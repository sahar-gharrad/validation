package com.esprit.examen.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.CategorieProduitRepository;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class SecteurActivityServiceTestMock {

    @Autowired
    SecteurActiviteServiceImpl secteurActiviteService;
    @MockBean
    SecteurActiviteRepository secteurActiviteRepository;


    @Test
    @Order(1)
    public void AjoutSecteurTest() {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code");
        sec.setLibelleSecteurActivite("libelle");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteService.addSecteurActivite(sec));
        log.info("Secteur activité est ajouté avec success");

    }
    @Test
    @Order(2)
    public void testModifiersecteurActivite(){

        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code 2");
        sec.setLibelleSecteurActivite("libelle 2");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteService.addSecteurActivite(sec));
        log.info("Secteur activité est ajouté avec success");

        sec.setCodeSecteurActivite("code1");
        sec.setLibelleSecteurActivite("libelle8");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteService.updateSecteurActivite(sec));
        log.info("Secteur Activité est modfié avec success");

    }

    @Test
    @Order(3)
    public void getAllSecteurActiviteTest() {
        SecteurActivite sec = new SecteurActivite();
        SecteurActivite sec1 = new SecteurActivite();
        sec.setCodeSecteurActivite("code1");
        sec.setLibelleSecteurActivite("libelle8");
        sec1.setCodeSecteurActivite("code1");
        sec1.setLibelleSecteurActivite("libelle8");
        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(Stream
                .of(sec, sec1).collect(Collectors.toList()));
        assertEquals(2, secteurActiviteService.retrieveAllSecteurActivite().size());
        List<SecteurActivite> listSecteurActivite = secteurActiviteService.retrieveAllSecteurActivite();
        log.info("==>size:" + listSecteurActivite.size());
        for (int i = 0; i < listSecteurActivite.size(); i++) {
            log.info("==>" + listSecteurActivite.get(i).getLibelleSecteurActivite());
        }
    }


    @Test
    @Order(4)
    public void deleteSecteurActiviteTest() {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code1");
        sec.setLibelleSecteurActivite("libelle8");
        assertNotNull(sec.getCodeSecteurActivite());
        assertNotNull(sec.getLibelleSecteurActivite());
        secteurActiviteService.deleteSecteurActivite(sec.getIdSecteurActivite());
        verify(secteurActiviteRepository, times(1)).deleteById(sec.getIdSecteurActivite());
        log.info("categorie supprimer avec success");
    }

    @Test
    @Order(5)
    public void deleteAllSecteurActiviteTest() {
        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(Stream
                .of(new SecteurActivite("code1", "secteur1"), new SecteurActivite("cat2", "categorie 3")).collect(Collectors.toList()));
        assertEquals(2, secteurActiviteService.retrieveAllSecteurActivite().size());
        List<SecteurActivite> listSecteurActivite = secteurActiviteService.retrieveAllSecteurActivite();
        log.info("==>size:"+listSecteurActivite.size());
        for(int i=0;i<listSecteurActivite.size();i++){
            secteurActiviteService.deleteSecteurActivite(listSecteurActivite.get(i).getIdSecteurActivite());;
            log.info("==> categorie "+listSecteurActivite.get(i).getLibelleSecteurActivite()+" deleted successfulyy ");
        }
    }
}










