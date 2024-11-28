package org.utiles

import io.github.cdimascio.dotenv.Dotenv

class EnvReader {
    static Dotenv dotenv = Dotenv.load()

    static String getEnvVar(String varName){
        return dotenv.get(varName)
    }
}
