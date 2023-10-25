# Welcome to StackEdit!

Hi! I'm your first Markdown file in **StackEdit**. If you want to learn about StackEdit, you can read me. If you want to play with Markdown, you can edit me. Once you have finished with me, you can create new files by opening the **file explorer** on the left corner of the navigation bar.


# Intro

La simulation se lance avec : 
**./swp durée tauxdeperte tauxderreurs trace duplex**
où les paramètres sont les suivants : 
**durée**: durée de la simulation en top d'horloges
**tauxdeperte**: pourcentage de trames perdues, entre 0 et 99
**tauxderreurs** pourcentage de trames reçues avec une erreur détectée trace
**ndrapeaux** pour tracer les événements (1 = émissions, 2 = réceptions, 4 = expirations, 8 = résumés périodiques)
**duplex** 0 pour une communication simplex 
On conseille d'utiliser la valeur 7 (1+2+4) pour trace.


## 2)

**2.1**
**2.2** **swp 700 25 0 7 0** => erreur au tick 517.
Le problème est que le réceptionneur peut décaler sa fenêtre et recevoir un paquet ancien à un nouvel emplacement. SWS est supérieur à NSEQ/2 donc il y a recouvrement "en  arrière" par la fenêtre d'émission.
**2.3** Si *SWS*=3 alors *SWS*<*NSEQ*/2 donc il ne peut pas y avoir de rebouclement

## 3)  Performances
*Efficacité en fonction de la taille de la fenêtre et du taux de perte.*
|RWS\tdp|0|2|4|6|8|10|12|15|20|
|--|--|--|--|--|--|--|--|--|--|
|1|100|47|46|44|40|38|37|35|34|
|2|100|62|60|56|53|50|49|45|41|
|3|100|78|74|70|64|61|60|55|49|
|4|100|93|86|82|77|72|68|63|54|


Script générant le tableau ci-dessus au format markdown
```
#!/bin/bash

log=$1
longueur=50000
echo "|RWS\tdp|0|2|4|6|8|10|12|15|20|" > $log
echo "|--|--|--|--|--|--|--|--|--|--|" > $log

for RWS in 1 2 3 4; do
	
	echo -n "|$RWS|" >> $log
	sed "s/RWS [0-9]/RWS $RWS/" -i swp.c
	make

	for tdp in 0 2 4 6 8 10  12 15 20; do
		./swp $longueur $tdp 0 7 0 >> /dev/null
		echo -n "$?|" >> $log
	done
	echo "" >> $log
done
```
```
#!/bin/bash

longueur=50000
sed -i '/plot.*/cplot "log1" w lp title "RWS1", "log2" w lp title "RWS2", "log3" w lp title "RWS3", "log4" w lp title "RWS4"' graph.plt

for RWS in 1 2 3 4; do
	echo "# RWS=$RWS"
	sed "s/RWS [0-9]/RWS $RWS/" -i swp.c
	make >> /dev/null
	for tdp in 0 2 4 6 8 10  12 15 20; do
		echo -n "$tdp " >> "log$RWS"
		./swp $longueur $tdp 0 7 0 >> /dev/null
		echo "$?" >> "log$RWS"
	done
done

gnuplot graph.plt
```

## 4) Le bug

En testant le code vu en cours avec le simulateur en mode simplex, on s'aperçoit qu'à partir du moment ou un *ACK* se perd, plus aucun *ACK* n'est envoyé, la communication n'avance donc plus.

## 5) Modication

**5.1** L'ajout du NAK permet à l'émetteur de renvoyer moins de paquets. 
Exemple UML
```mermaid
sequenceDiagram
A-x B: D0
A->>B: D1
A->>B: D2
A->>B: D3
B->>A: NAK 0
Note right of B: B signale à A qu'il n'à pas reçu D0
Note right of B: 0 1 2 3 4 5 6 7<br/>[. X X X] . . . .
A->>B: D0 
B->>A: ACK4


```

## Export a file

You can export the current file by clicking **Export to disk** in the menu. You can choose to export the file as plain Markdown, as HTML using a Handlebars template or as a PDF.


# Synchronization

Synchronization is one of the biggest features of StackEdit. It enables you to synchronize any file in your workspace with other files stored in your **Google Drive**, your **Dropbox** and your **GitHub** accounts. This allows you to keep writing on other devices, collaborate with people you share the file with, integrate easily into your workflow... The synchronization mechanism takes place every minute in the background, downloading, merging, and uploading file modifications.

There are two types of synchronization and they can complement each other:

- The workspace synchronization will sync all your files, folders and settings automatically. This will allow you to fetch your workspace on any other device.
	> To start syncing your workspace, just sign in with Google in the menu.

- The file synchronization will keep one file of the workspace synced with one or multiple files in **Google Drive**, **Dropbox** or **GitHub**.
	> Before starting to sync files, you must link an account in the **Synchronize** sub-menu.

## Open a file

You can open a file from **Google Drive**, **Dropbox** or **GitHub** by opening the **Synchronize** sub-menu and clicking **Open from**. Once opened in the workspace, any modification in the file will be automatically synced.

## Save a file

You can save any file of the workspace to **Google Drive**, **Dropbox** or **GitHub** by opening the **Synchronize** sub-menu and clicking **Save on**. Even if a file in the workspace is already synced, you can save it to another location. StackEdit can sync one file with multiple locations and accounts.

## Synchronize a file

Once your file is linked to a synchronized location, StackEdit will periodically synchronize it by downloading/uploading any modification. A merge will be performed if necessary and conflicts will be resolved.

If you just have modified your file and you want to force syncing, click the **Synchronize now** button in the navigation bar.

> **Note:** The **Synchronize now** button is disabled if you have no file to synchronize.

## Manage file synchronization

Since one file can be synced with multiple locations, you can list and manage synchronized locations by clicking **File synchronization** in the **Synchronize** sub-menu. This allows you to list and remove synchronized locations that are linked to your file.


# Publication

Publishing in StackEdit makes it simple for you to publish online your files. Once you're happy with a file, you can publish it to different hosting platforms like **Blogger**, **Dropbox**, **Gist**, **GitHub**, **Google Drive**, **WordPress** and **Zendesk**. With [Handlebars templates](http://handlebarsjs.com/), you have full control over what you export.

> Before starting to publish, you must link an account in the **Publish** sub-menu.

## Publish a File

You can publish your file by opening the **Publish** sub-menu and by clicking **Publish to**. For some locations, you can choose between the following formats:

- Markdown: publish the Markdown text on a website that can interpret it (**GitHub** for instance),
- HTML: publish the file converted to HTML via a Handlebars template (on a blog for example).

## Update a publication

After publishing, StackEdit keeps your file linked to that publication which makes it easy for you to re-publish it. Once you have modified your file and you want to update your publication, click on the **Publish now** button in the navigation bar.

> **Note:** The **Publish now** button is disabled if your file has not been published yet.

## Manage file publication

Since one file can be published to multiple locations, you can list and manage publish locations by clicking **File publication** in the **Publish** sub-menu. This allows you to list and remove publication locations that are linked to your file.


# Markdown extensions

StackEdit extends the standard Markdown syntax by adding extra **Markdown extensions**, providing you with some nice features.

> **ProTip:** You can disable any **Markdown extension** in the **File properties** dialog.


## SmartyPants

SmartyPants converts ASCII punctuation characters into "smart" typographic punctuation HTML entities. For example:

|                |ASCII                          |HTML                         |
|----------------|-------------------------------|-----------------------------|
|Single backticks|`'Isn't this fun?'`            |'Isn't this fun?'            |
|Quotes          |`"Isn't this fun?"`            |"Isn't this fun?"            |
|Dashes          |`-- is en-dash, --- is em-dash`|-- is en-dash, --- is em-dash|


## KaTeX

You can render LaTeX mathematical expressions using [KaTeX](https://khan.github.io/KaTeX/):

The *Gamma function* satisfying $\Gamma(n) = (n-1)!\quad\forall n\in\mathbb N$ is via the Euler integral

$$
\Gamma(z) = \int_0^\infty t^{z-1}e^{-t}dt\,.
$$

> You can find more information about **LaTeX** mathematical expressions [here](http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference).


## UML diagrams

You can render UML diagrams using [Mermaid](https://mermaidjs.github.io/). For example, this will produce a sequence diagram:

```mermaid
sequenceDiagram
Alice ->> Bob: Hello Bob, how are you?
Bob-->>John: How about you John?
Bob--x Alice: I am good thanks!
Bob-x John: I am good thanks!
Note right of John: Bob thinks a long<br/>long time, so long<br/>that the text does<br/>not fit on a row.

Bob-->Alice: Checking with John...
Alice->John: Yes... John, how are you?
```

And this will produce a flow chart:

```mermaid
graph LR
A[Square Rect] -- Link text --> B((Circle))
A --> C(Round Rect)
B --> D{Rhombus}
C --> D
```
