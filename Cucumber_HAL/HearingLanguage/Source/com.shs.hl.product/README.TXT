Achtung:
Beim Exportieren des Products können die folgenden Probleme den erfolgreichenExport verhindern.
 
- Beim Generieren des Editors wird im Plugin com.shs.hl.builtins in der Datei build.properties die Property 'source' auf den falschen 
Wert 'src' gesetzt. Dort muss 'model' stehen.
- Im Plugin com.shs.hl.generator wird in der build.properties auf das Verzeichnis 'src-gen' verwiesen. Dieses existiert aber nicht.
