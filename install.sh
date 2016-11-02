#!/usr/bin/env bash

PATH_MODULES="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )/../../"
[ ! -d $PATH_MODULES/drassil/joiner ] && git clone https://github.com/drassil/joiner $PATH_MODULES/drassil/joiner -b master
source "$PATH_MODULES/drassil/joiner/joiner.sh"

NAME="java-lib-common"
VENDOR="hw-core"

mod_path="hw-core/js-modules/"

#
# ADD DEPENDENCIES
#

Joiner:add_repo "https://github.com/HW-Core/java-lib-common.git"      "java-lib-common"                             "master" "$VENDOR"

Joiner:add_file "http://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar"      "$mod_path/apache-commons-lang3/commons-lang3-3.3.2.jar" 
Joiner:add_file "https://java.net/projects/swingx/downloads/download/releases/swingx-all-1.6.4.jar"                 "$mod_path/swingx/swingx-all-1.6.4.jar"

if Joiner:with_dev ; then
	Joiner:add_file "http://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2-javadoc.jar"      "$mod_path/apache-commons-lang3-doc/commons-lang3-3.3.2-javadoc.jar" 
fi
