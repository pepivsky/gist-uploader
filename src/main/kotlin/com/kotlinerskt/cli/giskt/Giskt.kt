package com.kotlinerskt.cli.giskt

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import java.io.File

class Giskt : CliktCommand(
    epilog = "Vamos a tratar de subir tus archivos"
) {

    private val gistFileName: File by argument(help = "Nombre del archivo gist a subir").file(mustExist = true)

    private val isPublic by option(
        names = arrayOf("-p", "--public"),
        help = "Necesitas guardar las nudes? aqui no es, solo dejalo privado y pon false"
    ).flag(default = true)

    private val description: String by option(
        names = arrayOf("-d", "--description"),
        help = "No es que necesitemos de tu ayuda, ni la demás gente, es más no pongas descripción"
    ).default("El mejor gist del mundo")

    override fun run() {
        createGisktFile(gistFileName)
            .createGistRequest(isPublic, description)
            .uploadFile()
    }
}