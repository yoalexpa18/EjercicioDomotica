package org.example.philips_hue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class HelloController {
    @FXML
    private Button botonSubirTemperatura;
    @FXML
    private Button botonBajarTemperatura;
    @FXML
    private Slider sliderTemperatura;
    @FXML
    private Text textTemperatura;
    @FXML
    private Button botonBajarGaraje;
    @FXML
    private Button botonSubirGaraje;
    @FXML
    private Button botonPlayPause;
    @FXML
    private Slider tiempoMusica;
    @FXML
    private Text textMusica;
    @FXML
    private Button botonOnOffTemperatura;
    @FXML
    private Button botonSalon;
    @FXML
    private Button botonCocina;
    @FXML
    private Button botonDomitorio;
    @FXML
    private Button botonExterior;


    @FXML
    public void initialize() {
        configurarSliderTemperatura();
        configurarBotonesTemperatura();
        configurarBotonesGaraje();
        configurarSlider();


        //Configuramos el evento del botón Play/Pause
        botonPlayPause.setOnAction(event -> cambiarImagenMusica());

        // Configuramos el evento del botón On/Off de temperatura
        botonOnOffTemperatura.setOnAction(event -> alternarEstadoTemperatura());

        //Botones encender apagar luces
        botonSalon.setOnAction(e -> alternarImagen(botonSalon, "Salon.png", "SalonOsc.png", estadoBoton1));
        botonCocina.setOnAction(e -> alternarImagen(botonCocina, "Cocina.png", "CocinaOsc.png", estadoBoton2));
        botonDomitorio.setOnAction(e -> alternarImagen(botonDomitorio, "Exterior.png", "ExteriorOsc.png", estadoBoton3));
        botonExterior.setOnAction(e -> alternarImagen(botonExterior, "Dormitorio.png", "DormitorioOsc.png", estadoBoton4));
    }


    private void configurarSliderTemperatura() {
        //Configurar el rango del slider
        sliderTemperatura.setMin(22);
        sliderTemperatura.setMax(32);

        //Valor inicial
        sliderTemperatura.setValue(23);

        //Mostrar el valor inicial en el texto
        textTemperatura.setText(String.format("%.0f °C", sliderTemperatura.getValue()));

        //Manejar eventos del slider
        sliderTemperatura.valueProperty().addListener((observable, oldValue, unused) -> {

            //Obtiene el valor actual del slider
            double valorActual = sliderTemperatura.getValue();
            textTemperatura.setText(String.format("%.0f °C", valorActual));

            //Habilitar o deshabilitar los botones según el valor del slider
            actualizarEstadoBotones();
        });
    }

    private void configurarBotonesTemperatura() {
        // Configurar acciones de los botones
        botonSubirTemperatura.setOnAction(event -> subirTemperatura());
        botonBajarTemperatura.setOnAction(event -> bajarTemperatura());

        //Actualizar el estado inicial de los botones
        actualizarEstadoBotones();
    }

    private void subirTemperatura() {
        double nuevaTemperatura = sliderTemperatura.getValue() + 1;

        //Asegurar que la nueva temperatura no exceda el máximo
        if (nuevaTemperatura <= sliderTemperatura.getMax()) {
            sliderTemperatura.setValue(nuevaTemperatura);
            textTemperatura.setText(String.format("%.0f °C", nuevaTemperatura));
        }
    }

    private void bajarTemperatura() {
        double nuevaTemperatura = sliderTemperatura.getValue() - 1;

        //Asegurar que la nueva temperatura no sea menor al mínimo
        if (nuevaTemperatura >= sliderTemperatura.getMin()) {
            sliderTemperatura.setValue(nuevaTemperatura);
            textTemperatura.setText(String.format("%.0f °C", nuevaTemperatura));
        }
    }

    //     Método para habilitar o deshabilitar los botones
    private void actualizarEstadoBotones() {
        if (sliderTemperatura.getValue() == sliderTemperatura.getMax()) {

            //Deshabilitar el botón de subir
            botonSubirTemperatura.setDisable(true);
        } else {

            //Habilitar el botón de subir
            botonSubirTemperatura.setDisable(false);
        }

        if (sliderTemperatura.getValue() == sliderTemperatura.getMin()) {

            //Deshabilitar el botón de bajar
            botonBajarTemperatura.setDisable(true);
        } else {

            //Habilitar el botón de bajar
            botonBajarTemperatura.setDisable(false);
        }
    }

    //Variable para controlar el estado del slider y los botones de temperatura
    private boolean temperaturaHabilitada = true;

    private void alternarEstadoTemperatura() {
        //Alternar el estado de habilitado/deshabilitado
        temperaturaHabilitada = !temperaturaHabilitada;

        //Actualizar el estado del slider y los botones
        sliderTemperatura.setDisable(!temperaturaHabilitada);
        botonSubirTemperatura.setDisable(!temperaturaHabilitada);
        botonBajarTemperatura.setDisable(!temperaturaHabilitada);

        //Cambiar la imagen del botón para indicar el estado actual
        String nuevaImagen = temperaturaHabilitada ? "OFF.png" : "ON.png";
        botonOnOffTemperatura.setStyle(
                "-fx-background-radius: 70; " +
                        "-fx-background-image: url('" + nuevaImagen + "'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center;"
        );
    }


    private void configurarBotonesGaraje() {
        //Inicializar el estado de los botones del garaje

        //El botón de subir empieza deshabilitado
        botonBajarGaraje.setDisable(true);

        //El botón de bajar empieza habilitado
        botonSubirGaraje.setDisable(false);

        //Configurar acciones de los botones
        botonBajarGaraje.setOnAction(event -> bajarGaraje());
        botonSubirGaraje.setOnAction(event -> subirGaraje());
    }

    private void bajarGaraje() {

        //Deshabilitar el botón de bajar y habilitar el botón de subir
        botonBajarGaraje.setDisable(true);
        botonSubirGaraje.setDisable(false);
    }

    private void subirGaraje() {

        //Deshabilitar el botón de subir y habilitar el botón de bajar
        botonSubirGaraje.setDisable(true);
        botonBajarGaraje.setDisable(false);
    }


    //Variable para controlar el estado de reproducción
    private boolean estaIniciado = false;

    private void cambiarImagenMusica() {

        if (estaIniciado) {

            //Cambiar la imagen a "pause"
            botonPlayPause.setStyle("-fx-background-radius: 150;-fx-background-image: url('pause.png'); -fx-background-size: cover; -fx-background-position: center;");
        } else {

            //Cambiar la imagen a "play"
            botonPlayPause.setStyle("-fx-background-radius: 150;-fx-background-image: url('play.png'); -fx-background-size: cover; -fx-background-position: center;");
        }

        //Alternar el estado
        estaIniciado = !estaIniciado;
    }

    private void configurarSlider() {

        //Establecemos el rango del slider
        //Mínimo: 0 segundos
        tiempoMusica.setMin(0);

        //Máximo: 210 segundos (3 minutos 30 segundos)
        tiempoMusica.setMax(210);

        //Valor inicial: 0 segundos
        tiempoMusica.setValue(0);

        //Valor inicial: 00:00 segundos
        textMusica.setText("00:00");

        //Configuramos el listener para actualizar el texto en textMusica
        tiempoMusica.valueProperty().addListener((observable) -> {
            //Usamos el valor actual del Slider
            int minutos = (int) tiempoMusica.getValue() / 60;
            int segundos = (int) tiempoMusica.getValue() % 60;
            String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);

            //Actualizamos el texto en textMusica
            textMusica.setText(tiempoFormateado);
        });
    }

    private boolean[] estadoBoton1 = {true};
    private boolean[] estadoBoton2 = {true};
    private boolean[] estadoBoton3 = {true};
    private boolean[] estadoBoton4 = {true};


    private void alternarImagen(Button boton, String imagen1, String imagen2, boolean[] estadoActual) {
        //Alternar el estado
        estadoActual[0] = !estadoActual[0];

        //Seleccionar la imagen según el estado
        String nuevaImagen = estadoActual[0] ? imagen1 : imagen2;

        //Cambiar el estilo del botón para actualizar la imagen
        boton.setStyle(
                "-fx-background-radius: 70; " +
                        "-fx-background-image: url('" + nuevaImagen + "'); " +
                        "-fx-background-size: 300 150; " +
                        "-fx-background-position: center;"
        );
    }
}
