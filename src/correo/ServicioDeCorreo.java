/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correo;

/**
 *
 * @author maximilianoa-te
 */
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class ServicioDeCorreo {

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String ENCODING = "utf-8";
    private Properties config;
    private String fromAddr;
    private String password;
    private boolean modoAutenticator = false;

    /**
     * Validate given email with regular expression.
     *
     * @param email email for validation
     * @return true valid email, otherwise false
     */
    public static boolean validarCorreo(String email) {

        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @param config
     */
    public ServicioDeCorreo(Properties config) {
        this.config = config;
    }

    /**
     *
     */
    public ServicioDeCorreo() {
    }

    /**
     *
     * @return
     */
    public boolean isModoAutenticator() {
        return modoAutenticator;
    }

    /**
     *
     * @param modoAutenticator
     */
    public void setModoAutenticator(boolean modoAutenticator) {
        this.modoAutenticator = modoAutenticator;
    }

    /**
     *
     * @return
     */
    public Properties getConfig() {
        return config;
    }

    /**
     *
     * @param config
     */
    public void setConfig(Properties config) {
        this.config = config;
    }

    /**
     *
     * @return
     */
    public String getFromAddr() {
        return fromAddr;
    }

    /**
     *
     * @param fromAddr
     */
    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param subject
     * @param content
     * @param listaCorreo
     * @return
     */
    public boolean enviarCorreo(String subject, String content, List<String> listaCorreo, List<File> listaArchivo) {

        try {

            config = getConfiguracion();

            if (listaCorreo.size() <= 0) {

                System.out.println("La lista de correo esta vacia ");

                return false;
            }

            Session session;
            Transport transport = null;
            if (modoAutenticator == true) {

                // Crear conexion con el servidor de correo
                session = Session.getInstance(config, new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromAddr,
                                password);
                    }
                });

            } else {

                session = Session.getDefaultInstance(config, null);
                transport = session.getTransport("smtp");
            }

            Message message = new MimeMessage(session);
            Address from = new InternetAddress(fromAddr);
            message.setFrom(from);

            for (String addr : listaCorreo) {

                Address toAddr = new InternetAddress(addr.trim());
                message.addRecipient(Message.RecipientType.TO, toAddr);
            }

            message.setSubject(subject);
            message.setSentDate(new Date());

            Multipart multiPartBody = new MimeMultipart();
            BodyPart body = new MimeBodyPart();
            body.setContent(content, "text/plain; charset=" + ENCODING);
            multiPartBody.addBodyPart(body);

            File file = listaArchivo.get(0);

            System.out.println("Archivo  " + file.getName() + " path " + file.getAbsolutePath());

            BodyPart mdp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(file);
            DataHandler dh = new DataHandler(fds);
            mdp.setDataHandler(dh);
            mdp.setFileName(MimeUtility.encodeText(file.getName(),
                    ENCODING, "B"));
            multiPartBody.addBodyPart(mdp);

            System.out.println("Contenido para enviar " + multiPartBody);
            message.setContent(multiPartBody);
            message.addHeader("user-agent", "MailIRC");

            if (modoAutenticator == true) {

                Transport.send(message);

            } else {

                if (transport != null) {

                    transport.connect(fromAddr, password);
                    transport.sendMessage(message, message.getAllRecipients());

                } else {
                    System.out.println("SMTP fallo !!!" + transport);
                }
            }

            return true;

        } catch (MessagingException | UnsupportedEncodingException ex) {

            Logger.getLogger(ServicioDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean enviarCorreoFact(String subject, String content, String correoCliente, File file) {
     
         //si tira este error : javax.mail.AuthenticationFailedException: failed to connect
        //Tiene que decirle a gmail que te deje conectar con esta aplicacion
        //Paso : cuenta ->seguridad -> Acceso de apps menos seguras
        Boolean enviado = false;
        
        try {

            config = getConfiguracion();
            setFromAddr("clientesightrack@gmail.com");
            setPassword("ftvxblqtarnyepel");

//                        setFromAddr("ninomax0529@gmail.com");
//                        setPassword("bfbgmwffobsbivks");
            
            Session session;
            Transport transport = null;
            if (modoAutenticator == true) {

                // Crear conexion con el servidor de correo
                session = Session.getInstance(config, new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromAddr,
                                password);
                    }
                });

            } else {

                session = Session.getDefaultInstance(config, null);
                transport = session.getTransport("smtp");
            }

            Message message = new MimeMessage(session);
            Address from = new InternetAddress(fromAddr);
            message.setFrom(from);

//            for (String addr : listaCorreo) {
//
            Address toAddr = new InternetAddress(correoCliente.trim());
            message.addRecipient(Message.RecipientType.TO, toAddr);
//            }

            message.setSubject(subject);
            message.setSentDate(new Date());

            Multipart multiPartBody = new MimeMultipart();
            BodyPart body = new MimeBodyPart();
            body.setContent(content, "text/plain; charset=" + ENCODING);
            multiPartBody.addBodyPart(body);

//            File file = listaArchivo.get(0);
            System.out.println("Archivo  " + file.getName() + " path " + file.getAbsolutePath());

            BodyPart mdp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(file);
            DataHandler dh = new DataHandler(fds);
            mdp.setDataHandler(dh);
            mdp.setFileName(MimeUtility.encodeText(file.getName(),
                    ENCODING, "B"));
            multiPartBody.addBodyPart(mdp);

            System.out.println("Contenido para enviar " + multiPartBody);
            message.setContent(multiPartBody);
           
            message.addHeader("user-agent", "MailIRC");

            if (modoAutenticator == true) {

                try {

                    Transport.send(message);
                    enviado = true;

                } catch (Exception e) {
                    enviado = false;
                    e.printStackTrace();
                }

            } else {

                if (transport != null) {

                    try {

                        transport.connect(fromAddr, password);
                        transport.sendMessage(message, message.getAllRecipients());
                        enviado = true;

                    } catch (Exception e) {
                        enviado = false;
                        e.printStackTrace();
                    }

                } else {

                    System.out.println("SMTP fallo !!!" + transport);
                    enviado = false;
                }
            }

        } catch (MessagingException | UnsupportedEncodingException ex) {

            enviado = false;
            Logger.getLogger(ServicioDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return enviado;
    }

    public static Properties getConfiguracion() {

        Properties props = new Properties();
        try {

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

//
//            ServidorDeCorreo sc = ManejoServidorCorreo.getInstancia().getServidorDeCorreo();
////
////            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
////            properties.put("mail.smtp.socketFactory.fallback", "false");
////            properties.put("mail.smtp.port", "587");
////            properties.put("mail.smtp.socketFactory.port", "587");
//
//            properties.put("smtp.starttls.enable", "false");
//            properties.put("mail.smtp.host", sc.getServidor());
////            properties.put("mail.smtp.socketFactory.port", sc.getPuerto());
//            properties.put("mail.smtp.port", sc.getPuerto());
//            properties.put("mail.smtp.auth", "false");
////            properties.put("mail.smtp.user", "maxsoft0529@gmail.com");
////            properties.put("mail.smtp.password", "@maxsoft1234567890");
//            properties.put("mail.debug", "true");
        } catch (Exception e) {

            e.printStackTrace();
        }

        return props;
    }

    private static Calendar getHoraEjecucion(int hora, int minutos) {
        Calendar horaDeEjecucionDiaria = new GregorianCalendar();
        horaDeEjecucionDiaria.set(Calendar.HOUR_OF_DAY, hora);
        horaDeEjecucionDiaria.set(Calendar.MINUTE, minutos);
        horaDeEjecucionDiaria.set(Calendar.SECOND, 0);
        horaDeEjecucionDiaria.set(Calendar.MILLISECOND, 0);
        return horaDeEjecucionDiaria;
    }

    public static void main(String[] args) {

        System.out.println("Hora de ejecucion " + ServicioDeCorreo.getHoraEjecucion(8, 30).getTime());
//
//        List<ConfiguracionCorreo> listaConfiguracionCorreo = ManejoConfiguracionCorreo.getInstancia().getListaCorreo();
//
//        List<String> listaCorreo = new ArrayList<>();
//
//        for (ConfiguracionCorreo cc : listaConfiguracionCorreo) {
//
//            listaCorreo.add(cc.getCorreo());
//        }
//
        ServicioDeCorreo sc = new ServicioDeCorreo();
//
//        sc.setFromAddr("maximilianoa@cementoscibao.com");
//
//        sc.setPassword("Ninomax0529@");
        List<File> listaArch = null;
        List<String> listaCoreo = null;
        sc.enviarCorreoFact("ejemplo", "corre factura", "ninomax0529@gmail.com", new File("C://rpd/FacturaCliente_272107.pdf"));
//
//        //        sc.setModoAutenticator(true);
//        //        sc.setConfig(sc.getConf);
//        File file = new File("C:\\consulta produccion\\reporteProduccionEquipo.xls");
//
//        List<File> lista = new ArrayList<>();
//        lista.add(file);
//
//        System.out.println("Leer archivo ");
//     
//        System.out.println("Termine de Leer archivo ");
//
//        sc.setFromAddr("maximilianoa@cementoscibao.com");
//
//        sc.setPassword("Nino0529");

//        sc.enviarCorreo("Correo de java ", "Nitido este correo ", listaCorreo);
    }
}
