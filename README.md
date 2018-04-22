# PracticaStreaming

paso 1: importar el proyecto en intellij
paso 2: al importarlo, pinchamos en build, build artifacts..., build
paso 3: intellij nos creará un .jar en la ruta: out/artifacts/streamingkafka_jar
paso 4: para instalar spark, seguí los pasos de este tutorial:

http://www.exegetic.biz/blog/2017/07/installing-spark-ubuntu/

paso 5: una vez instalado, ejecutamos el comando:
$SPARK_HOME/bin/spark-submit --class PrintTweets /home/sbd/Escritorio/twitter/streamingkafka.jar
