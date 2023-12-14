# NE424 - TP2 :  PPP
### Intro
Dans ce TP nous allons explorer les bases et principes du protocole PPP ainsi qu'une encapsulation de ce service dans le protocole Ethernet avec PPPoE.
Depuis notre machine de TP nous utiliserons PPPoE pour créer un tunnel PPPoE avec un concentrateur d'accès. Puis nous utiliserons le protocole PPP pour négocier une adresse IP et obtenir un accès internet.
### Prépa
##### [P1] Qu'est-ce que le "Linux cooked-mode capture" ? Dans quel cas est-il utile ?
Le SLL est un pseudo protocole utilisé par la libpcap pour capturer des paquets sur certains "network devices" où le lien natif ne fourni pas certains headers de façon stable.
Il est par exemple utilisé pour le protocole PPP sur Linux.
##### [P2] Quel doit être le MTU de l'interface PPP
Le MTU de l'interface PPP ne doit pas être supérieur à 1492, car la taille maximum d'une payload Ethernet est de 1500 octets. Si on enlève les 6 octets du header PPP et les 2 octets du Protocole ID, il ne reste que 1492 octets utilisable par PPP.
##### [P3] Quelle est l'effet de l'option defaultroute
Cette option ajoute une route par défaut à la table de routage du système, en utilisant le pair PPP comme gateway.
L'entrée est supprimée si la connexion est perdue.
Cette option n'est pas toujours désirée car on pourrait avoir une autre interface sur notre machine, et on pourrait préférer que les paquets passe par cette autre interface plutôt que par notre liaison PPP.
##### [P4] Au cours d'une négociation LCP, quels sont les principaux types de messages ? Que signifient-ils ?
1) Configure-Request
Ouvre une connexion PPP, contient une liste d'options LCP.
2) Configure-Ack
Envoyé si toutes les options du dernier «Configure-Request» ont été acceptées. Quand les deux pairs ont reçu un «Configure-Ack» la négociation est complète.
3) Configure-Nak
Envoyé si certainnes options LCP ne sont pas acceptées. Le message contient les options qu'il accepte.
4) Configure-Reject
Envoyé si certaines options LCP ne sont pas reconnue ou non négociable. Le message contient les options rejetés.
### Questions
##### R1 → Donnez l'adresse MAC de l'interface Ethernet utilisée, l'adresse IP de l'interface ppp0, l'adresse IP du serveur.
``$ ifconfig``
MAC de eth0:&nbsp; **A0:F3:C1:01:E9:D9**
IP de ppp0: &nbsp;&nbsp;&nbsp;&nbsp; **10.0.2.217**
IP du serveur:&nbsp; **10.0.1.217**

##### R2 → Relevez et commentez un diagramme d'échange détaillé montrant toute la négociation LCP.
On analyse simplement le traffic sur eth0 à partir du moment ou on lance ``$ pon dsl-provider``, jusqu'au trames de type «Configure-Ack».
-- ```Annexe: Schéma 1```

##### R3 → Relevez et commentez le diagramme d'échange complet de la communication quand vous refusez tous les protocoles d'authentification.
Pour refuser les protocoles d'authentification, on ajoute les lignes ``refuse-xxx``, avec "xxx" le protocole, au fichier `/etc/ppp/peers/dsl-provider`
-- ```Annexe: Schéma 2```

##### R4 → Avec quels protocoles le serveur accepte-t-il de s'authentifier ?
Pour savoir avec quels protocole le serveur acceptait de s'authentifier, nous avons fait plusieurs essais. En modifiant le fichier de configuration pour ne laisser qu'un seul choix au serveur, nous avons pu déduire que le serveur n'acceptait que les protocoles *eap*,*pap* et *chap*.

##### R5 → Relevez et commentez un diagramme d'échange détaillé montrant toute la négociation IPCP.
La phase de négociation IPCP à lieu juste après la phase d'authentification.
C'est à ce moment que les deux pairs décident de leur addresses IP ainsi que d'autres paramètres.
Il faut se rappeler que les deux pair commence leur propres négociations de chaque coté, par soucis de clarté nous avons séparé ces deux négociations en deux schémas respectifs.
```Annexe: Schéma 3a et 3b```

##### R6 → Où est stockée l'adresse du serveur DNS primaire ?
L'outil de configuration ``$ pppoeconf`` nous indique que l'IP du serveur DNS reçue est stockée dans `/etc/ppp/resolv.conf`

##### R7 → Combien de NAK votre machine émet elle pour demander au serveur de prendre l'adresse que vous avez choisie ?
En analysant la négociation IPCP on réalise que notre machine envoie plus d'une centaine de NAK avant d'abandonner la négociation.

##### R8 → Expliquez la différence entre la session PPPoE et le lien PPP.
La session PPPoE est ouverte sur la couche IP de la machine, cette session est une connection entre deux machine du réseau local. Elle n'est pas censé être utilisé par autre chose que le démon pppoed qui s'en sert pour émuler une couche liaison qui sera utilisé par le lien PPP. Ce lien PPP désigne la connexion virtuelle entre ces deux machines. Les application utilise le protocole PPP sur cette couche virtuelle comme si l'interface PPP, à la quelle est attachée le lien, était une vraie interface (comme une carte réseau par exemple).
##### R9 → PPP est-il un protocole client-serveur ? et PPPoE ?
PPP n'est pas un protocole client-serveur ce protocole permet uniquement de faire communiquer deux hôtes dans une liaison point à point.
Par contre PPPoE est bien un protocole client-serveur, on à bien une machine qui va émettre une requête auprès d'un serveur, afin de créer une liaison PPP, de plus plusieurs clients peuvent se connecter au même serveur (simultanément).
##### R10 → (rappels NE323) La route par défaut est une route directe. Ne devrait-on pas plutôt avoir une route indirecte avec comme prochain pas le concentrateur d'accès ? Est-ce que cela changerait quelque chose ?
Avoir une route indirecte avec le concentrateur d’accès comme prochain pas serait possible, mais pas nécessaire.
En effet, l’interface ppp0 donne accès à un lien direct vers le gateway. Nous communiquons directement en point à point au destinataire via cette interface. Transmettre des données sur cette interface mène directement au concentrateur d’accès qui l’enverra au bon destinataire. Ajouter de la précision dans la table de routage est donc inutile.

### Schémas
#### Schéma 1: négociation LCP
![schema1](https://cdn.discordapp.com/attachments/671839075384164365/1029425471282221136/flow1.png)
#### Schéma 2: refus des protocoles d'authentifications
![schema2](https://media.discordapp.net/attachments/671839075384164365/1029425471861043230/flow2.png?width=441&height=473)
#### Schéma 3 négociations IPCP\
##### a)
![schema3a](https://cdn.discordapp.com/attachments/671839075384164365/1029425472347582474/flow3a.png)
##### b)
![schema3b](https://cdn.discordapp.com/attachments/671839075384164365/1029425470787301498/flow3b.png)
