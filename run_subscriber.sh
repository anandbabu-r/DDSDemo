#!/bin/sh
#
#                             Vortex Cafe
#
#    This software and documentation are Copyright 2010 to 2015 PrismTech
#    Limited and its licensees. All rights reserved. See file:
#
#                           docs/LICENSE.html
#
#    for full copyright notice and license terms.
#

/local/babu/branch-8/petalinux_ful/target/linux-x86_64/bin/java \
      -Dlog.level=DEBUG \
      -Dddsi.network.transport=tcp \
      -Dddsi.discovery.tcp.port=7411 \
      -Dddsi.discovery.externalNetworkAddresses=none \
     -Dddsi.discovery.tcp.peers=demo-eu.prismtech.com:7400 \
     -Ddds.partition=Aicas-HelloWorld-test \
     -cp target/classes:../../lib/cafe.jar:/local/babu/customers/Embeddedworld/vortext_tryagain/slf4j/slf4j-1.7.14/slf4j-simple-1.7.14.jar helloworld.HelloWorldSubscriber

