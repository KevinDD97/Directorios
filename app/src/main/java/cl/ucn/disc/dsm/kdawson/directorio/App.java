package cl.ucn.disc.dsm.kdawson.directorio;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.data.StringFormat;

/*
 * Main App.
 *
 * @author Kevin Dawson Diaz
 * */

public class App extends Application {
/*
*
* @param base context to use
*
*
* */
     @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // ACRA CONFIGURATION
        CoreConfigurationBuilder builder = new CoreConnectionBuilder(arg():this)

         builder
                 .withBuildConfigClass(BuildConfig.class)
                 .withReportFormat(StringFormat.JSON)
                 .withEnable(true);


         // ACRA DIALOG CONFIGURATION
         builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class)
                 //required
                 .withEnabled(true)
                 //required
                 .withResText(R.string.acra_dialog_title)
                 //optional, enables the email input
                 .withResEmailPrompt(R.string.acra_dialog_comment);

         // ACRA EMAIL CONFIGURATION

         builder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder.class)
                 .withMailTo("kevin.dawson@alumnos.ucn.cl")
                 .withReportFileName("crash.txt")
                 .withSubject(getString(R.string.acra_dialog_title))
                 .withBody(getString(R.string.acra_dialog_comment))
                 .withEnable(true);

        // The following line triggers the initialization of ACRA
        ACRA.init(app:this, builder);



    }
}
