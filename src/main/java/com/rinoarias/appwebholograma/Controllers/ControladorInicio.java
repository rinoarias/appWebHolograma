package com.rinoarias.appwebholograma.Controllers;

import com.rinoarias.appwebholograma.Driver.DriverFan;
import com.rinoarias.appwebholograma.Repositories.CategoriaRepository;
import com.rinoarias.appwebholograma.Services.Implementations.CategoriaService;
import com.rinoarias.appwebholograma.entities.Categoria;
import com.rinoarias.appwebholograma.entities.Imagen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

//@Component
@Controller
@Slf4j
public class ControladorInicio {
    private DriverFan controller;
    @Autowired

    private CategoriaService categoriaService;

    // Pantalla Inicio
    @GetMapping("/")
    public String inicio(Model model){
        String saludar = "Hola Mundo desde Thymeleaf";
        log.info("Enviando mensaje");
        model.addAttribute("mensaje", saludar);
        return "index";
    }

    // Pantalla Media Player
    @GetMapping("/player")
    public String reproductorControl(Model model){
        log.info("Pantalla player");
        return "player";
    }

    // Pantalla Listar Archivos
    @GetMapping("/listFiles")
    public String pantallaListarArchivos(Model model){
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "listFiles";
    }

    @GetMapping("/categoria/{categoriaID}")
    public String pantallaListarImagenesPorIdCategoria(@PathVariable("categoriaID") int categoriaID, Model model){
        List<Imagen> imagenes = categoriaService.findAllImagenesByCategoriaId(categoriaID);
        model.addAttribute("imagenes", imagenes);
        return "listFilesByCategoria";
    }



    @PostMapping("files")
    public String getFiles(Model model){
        controller = new DriverFan();
        String responseFetchVideoList = controller.fetchVideoList();
        System.out.println("responseFetchVideoList = " + responseFetchVideoList);
        model.addAttribute("listadoArchivos", obtenerListadoArchivos(responseFetchVideoList));
//        var listadoArchivos = asList("000001.bin",
//                "000002.bin",
//                "000003.bin",
//                "000004.bin",
//                "000005.bin",
//                "000006.bin",
//                "000007.bin",
//                "000008.bin",
//                "000009.bin",
//                "000010.bin",
//                "000011.bin",
//                "000012.bin",
//                "000013.bin",
//                "000014.bin",
//                "000015.bin",
//                "000016.bin",
//                "000017.bin",
//                "000018.bin");
//        model.addAttribute("listadoArchivos", listadoArchivos);
        return "index";
    }

    @GetMapping("playVideo/{videoID}")
    public String selectVideo(@PathVariable("videoID") String videoID){
        controller = new DriverFan();
        System.out.println("controller.playVideoWithId(videoID); = " + controller.playVideoWithId(videoID));
        return "redirect:/listFiles";
    }


    @PostMapping("playVideo")
    public String selectVideo(@RequestParam("numVideo") String videoID, Model model) {
        controller = new DriverFan();
        model.addAttribute("respuesta", controller.playVideoWithId(videoID));
        return "redirect:/player";
    }

    @GetMapping("playLoopMode")
    public String selectLoopMode(){
        controller = new DriverFan();
        System.out.println(controller.selectLoopPlaybackMode());
        return "redirect:/player";
    }

    @GetMapping("playSingleVideoModel")
    public String selectSinglePlayModel(){
        controller = new DriverFan();
        System.out.println(controller.selectSingleVideoPlaybackMode());
        return "redirect:/player";
    }

    @GetMapping("bajarBrillo")
    public String bajarBrillo(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerLightUp());
        return "redirect:/player";
    }

    @GetMapping("subirBrillo")
    public String subirBrillo(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerLightUp());
        return "redirect:/player";
    }

    @GetMapping("imagenAnterior")
    public String imagenAnterior(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerPlaylast());
        return "redirect:/player";
    }

    @GetMapping("siguienteImagen")
    public String nextImage(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerPlayNext());
        return "redirect:/player";
    }

    @GetMapping("play")
    public String playImage(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerPlayStart());
        return "redirect:/player";
    }

    @GetMapping("pause")
    public String pauseImage(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerPlayPause());
        return "redirect:/player";
    }


    private List<String> obtenerListadoArchivos(String stringFileList){
        List<String> filenames = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d{6}\\.bin");
        Matcher matcher = pattern.matcher(stringFileList);

        while (matcher.find()) {
            filenames.add(matcher.group());
        }
        return filenames;
    }
}
