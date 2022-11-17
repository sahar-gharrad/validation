package com.esprit.examen.services;

import java.text.ParseException;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.SecteurActivite;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class SecteurActivityServiceImpTest {

    @Autowired
    ISecteurActiviteService  secteurActiviteService;


    @Test
    @Order(1)
    public void testAddsecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code");
        sec.setLibelleSecteurActivite("libelle");
        SecteurActivite x = secteurActiviteService.addSecteurActivite(sec);
        assertNotNull(x.getCodeSecteurActivite());
        assertNotNull(x.getLibelleSecteurActivite());
        log.info("categorie ajouter avec success");
    }

    @Test
    @Order(2)
    public void testModifiersecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code 2");
        sec.setLibelleSecteurActivite("libelle 2");
        secteurActiviteService.addSecteurActivite(sec);
        log.info("code modifier avec success");
        sec.setCodeSecteurActivite("code 3");
        sec.setLibelleSecteurActivite("libelle 3");
        SecteurActivite x = secteurActiviteService.updateSecteurActivite(sec);
        assertNotNull(x.getCodeSecteurActivite());
        assertNotNull(x.getLibelleSecteurActivite());
        log.info("code modifier avec success");
    }

    @Test
    @Order(3)
    public void testRetrieveAllsecteurActivite() throws ParseException {
        List<SecteurActivite> listSecteurActivite = secteurActiviteService.retrieveAllSecteurActivite();
        Assertions.assertNotEquals(0, listSecteurActivite.size());
        log.info("Nombre ActiviteService: " + listSecteurActivite.size()+" \n");
        for(int i=0;i<listSecteurActivite.size();i++){
            log.info("==>"+listSecteurActivite.get(i).getCodeSecteurActivite());
        }
    }

    @Test
    @Order(4)
    public void testDeletesecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("code 3");
        sec.setLibelleSecteurActivite("libelle 3");
        secteurActiviteService.addSecteurActivite(sec);
        secteurActiviteService.deleteSecteurActivite(sec.getIdSecteurActivite());
        log.info("secteur supprimer avec success");
    }

}
