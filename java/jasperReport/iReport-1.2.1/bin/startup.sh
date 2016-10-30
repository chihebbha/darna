#! /bin/sh


# add the libraries to the IREPORT_CLASSPATH.
# EXEDIR is the directory where this executable is.
EXEDIR=${0%/*}
DIRLIBS=${EXEDIR}/../lib/*.jar
for i in ${DIRLIBS}
do
  if [ -z "$IREPORT_CLASSPATH" ] ; then
    IREPORT_CLASSPATH=$i
  else
    IREPORT_CLASSPATH="$i":$IREPORT_CLASSPATH
  fi
done

DIRLIBS=${EXEDIR}/../lib/*.zip
for i in ${DIRLIBS}
do
  if [ -z "$IREPORT_CLASSPATH" ] ; then
    IREPORT_CLASSPATH=$i
  else
    IREPORT_CLASSPATH="$i":$IREPORT_CLASSPATH
  fi
done

IREPORT_CLASSPATH="${EXEDIR}/../classes":${EXEDIR}/../fonts:$IREPORT_CLASSPATH
IREPORT_HOME="${EXEDIR}/.."

java -classpath "$IREPORT_CLASSPATH:$CLASSPATH" -Direport.home=$IREPORT_HOME it.businesslogic.ireport.gui.MainFrame "$@"
