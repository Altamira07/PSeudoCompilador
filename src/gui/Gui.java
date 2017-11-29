package gui;


import compilador.lexico.AnalisisLexico;
import compilador.sintactico.Sintactico;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.*;

public class Gui extends Application {
    Stage ventana;
    MenuBar menuBar;
    BorderPane root;
    Button btnLexico,btnSintactico,btnSemantico;
    TextArea salida;
    CodeArea codeArea = new CodeArea();
    TableView<TokenT> tablaSimbolos;
    File archivo;
    AnalisisLexico aLexico = new AnalisisLexico();
    Sintactico aSintactico = new Sintactico(null);
    final String ok = "-fx-background-color:#58FA58",error= "-fx-background-color:#B43104",def="-fx-background-color:#FFFFFF";
    @Override
    public void start(Stage primaryStage) throws Exception{
        ventana = primaryStage;
        root = new BorderPane();

        inicializar();

        Scene scene = new Scene(root,920,560);
        //Configuraciones
        ventana.setTitle("Compilador luisito");
        ventana.setScene(scene);
        ventana.show();

    }
    void inicializar()
    {
        menuBar = new MenuBar();
        root.setTop(menuBar);
        menuBar.getMenus().addAll(getMenuArchivo(),getMenuCompilar());
        //Botones que van a cambiar de color
        btnLexico = new Button("Lexico");
        btnSemantico = new Button("Semantico");
        btnSintactico = new Button("Sintactico");
        btnLexico.setDefaultButton(false);
        btnSemantico.setDefaultButton(false);
        btnSintactico.setDefaultButton(false);
        HBox  hBoxButtons = new HBox();
        hBoxButtons.setSpacing(5);
        VBox pnlIzq = new VBox();
        hBoxButtons.getChildren().addAll(btnLexico,btnSintactico,btnSemantico);
        pnlIzq.getChildren().add(hBoxButtons);


        VBox pnlCentral = new VBox();
        Label lblSalida = new Label("Salida");

        salida = new TextArea();
        salida.setPrefHeight(200);

        pnlCentral.getChildren().addAll(codeArea,lblSalida,salida);
        codeArea.setPrefHeight(400);
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        root.setCenter(pnlCentral);

        tablaSimbolos = new TableView();
        tablaSimbolos.setEditable(false);

        TableColumn<TokenT,String> id = new TableColumn<>("Id") ;
        TableColumn<TokenT,String> lexema = new TableColumn<>("Lexema") ;
        TableColumn<TokenT,String> tipo = new TableColumn<>("Tipo") ;
        tablaSimbolos.getColumns().addAll(id,lexema,tipo);
        pnlIzq.getChildren().add(tablaSimbolos);
        tablaSimbolos.setPrefHeight(500);
        root.setRight(pnlIzq);
    }
    Menu getMenuArchivo()
    {
        Menu menu = new Menu("Archivo");
        MenuItem nuevo = new MenuItem("Nuevo");
        nuevo.setOnAction(event -> {
            archivo = null;
            codeArea.replaceText("");
        });
        MenuItem abrir = new MenuItem("Abrir");
        abrir.setOnAction(event -> {
            abrir();
        });
        MenuItem guardar = new MenuItem("Guardar");
        guardar.setOnAction(event -> {
            guardarDatos();
        });
        MenuItem salir = new MenuItem("Salir");
        salir.setOnAction(actionEvent -> Platform.exit());
        menu.getItems().addAll(nuevo,abrir,guardar,salir);
        return  menu;
    }

    void abrir()
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("LH","*.lh"));
        archivo = fileChooser.showOpenDialog(ventana);
        String cadena = "";
        String ed = "";
        if(archivo != null)
        {
            try{
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                while ((cadena = br.readLine())!= null){
                    ed+=cadena+"\n";
                }
                br.close();
                codeArea.replaceText(ed);
            }catch (IOException io){
                Alert alert  = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No se pudo abrir");
                alert.showAndWait();
            }
            ventana.setTitle("Compilador luisito -"+archivo.getName());
        }

    }
    void guardarDatos()
    {

        String data = codeArea.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(archivo != null)
        {
            try{
                PrintWriter pw = new PrintWriter(archivo);
                pw.print(data);
                pw.close();
                String path = archivo.getPath();
                archivo = new File(path);
                alert.setContentText("Guardado con exito");
            }catch (IOException io){
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("No se pudo guardar");
            }
        }else{
            FileChooser fileChooser = new FileChooser();
            archivo = fileChooser.showSaveDialog(ventana);
            if(archivo != null){
                try{
                    FileWriter fw = new FileWriter(archivo,false);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.print(data);
                    pw.close();
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Guardado con exito");
                }catch (IOException IO){
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setContentText("Error al intentar guardar :v");
                }

            }
        }
        if(archivo != null)
            ventana.setTitle("Compilador luisito -"+archivo.getName());
            else
            ventana.setTitle("Compilador luisito -");

        alert.showAndWait();

    }


    Menu getMenuCompilar()
    {
        Menu menu = new Menu("Compilar");
        MenuItem run = new MenuItem("run");
        run.setOnAction(event -> {
            run();
        });
        MenuItem lexico = new MenuItem("Lexico");
        MenuItem sintactico = new MenuItem("sintactico");
        MenuItem semantico = new MenuItem("semantico");
        menu.getItems().addAll(run);
        return menu;
    }
    void run(){
        salida.clear();
        btnSintactico.setStyle(null);
        btnLexico.setStyle(null);
        if(archivo != null)
        {
            aLexico.analizar(archivo);
            if(aLexico.getError().equals(""))
            {
                btnLexico.setStyle(ok);
                aSintactico.setTokens(aLexico.getTb().getTk());
                aSintactico.instrucciones();
                if(aSintactico.getError().equals("")){
                    btnSintactico.setStyle(ok);
                }else{
                    btnSintactico.setStyle(error);
                    salida.setText(aSintactico.getError());
                }
            }else{
                btnLexico.setStyle(error);
                salida.setText(aLexico.getError());
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Debes guardar el archivo o abrir uno");
            alert.showAndWait();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
