# Suivi de niveau — Formation Spring Boot / Git

## Contexte élève
- Apprend en mode précepteur (socratique strict).
- A écrit le CRUD initial (Produit/Livre) avec assistance IA — estimation "80% autonome" à valider concrètement, pas encore testée en pratique réelle (seulement en questions/réponses orales).
- Environnement : Windows + IntelliJ + Git Bash (MINGW64).

## Séance du 2026-09-25

### Sujets abordés
- Test diagnostic Spring Boot (couches, JPA, `ddl-auto`, query derivation)
- Git de zéro : `.git` vs `.gitignore`, les 3 zones (working dir / staging / repo), cycle add/commit/push, branches (création, isolation, merge), navigation Git Bash sous Windows

### Niveau évalué

**Spring Boot : fragile, connaissances d'usage sans les mécanismes.**
- Comprend correctement le flux en couches (Controller→Service→Repository→BD) et sait l'expliquer avec ses mots.
- Ne connaissait pas la valeur par défaut de `ddl-auto` ni où elle se configure — pensait l'avoir configurée alors que le fichier `application.properties` ne contient quasiment rien. Léger signal d'affirmation prématurée (a affirmé "j'ai mis à jour telle ligne" avant de vérifier, puis a découvert que non).
- Sur la query derivation (`findByNom`), a d'abord donné des réponses vagues ("Spring écrit les requêtes à l'exécution") mais a réussi, par une série de questions guidées, à reconstruire correctement le mécanisme complet (proxy dynamique au démarrage, parsing du nom de méthode en SELECT/WHERE/champ, erreur au démarrage et non à la compilation). **Capacité de raisonnement déductif solide une fois bien guidé — c'est un point fort à exploiter.**
- N'a pas encore vérifié lui-même la doc officielle Spring Boot sur `ddl-auto` (demandé, non confirmé en retour) — à relancer.

**Git : débutant complet au départ → bases opérationnelles acquises en une séance.**
- Aucune connaissance préalable, y compris la commande `ls`. A dû apprendre `cd`, `ls -a`, la conversion de chemins Windows→Git Bash (`D:\...` → `/d/...`) — bloqué plusieurs tours, résolu avec indices progressifs (niveau 4-5 : pointeur vers `ls --help`).
- A confondu `.gitignore` et `.git` au début (pensait que la présence de `.gitignore` prouvait le suivi Git) — corrigé après questionnement, mais cette confusion mérite d'être retestée plus tard sans aide.
- A exécuté correctement et sans erreur bloquante tout le cycle : `git status`, `git add`, `git commit -m`, `git push`, `git switch -c`, `git switch`, `git merge`, avec vérification indépendante côté serveur (GitHub) par le formateur.
- Bon réflexe observé spontanément : a remarqué `modified: .gitignore` inattendu dans `git status` avant un commit et a demandé pourquoi au lieu de commit à l'aveugle (bonne hygiène, à encourager).
- Confusion notable : a d'abord attribué l'isolation entre branches locales au fait de ne pas avoir fait `git push` (confond synchronisation locale/distante avec isolation des branches). S'est corrigé seul après reformulation de la question, en retrouvant sa propre définition antérieure de "branche = pointeur vers un commit".
- N'a pas encore vu : gestion des conflits de merge, `git pull`, `git clone`, `.gitignore` en profondeur, remotes multiples, rebase.

### Patterns d'erreur récurrents à surveiller
1. **Affirmation avant vérification** : tendance à répondre "je pense que c'est X" avec assurance avant de vérifier dans le code/la doc (vu sur `ddl-auto`, sur l'origine de la modif `.gitignore`). Ce n'est pas un manque de capacité — quand poussé à vérifier, il vérifie correctement et se corrige. À travailler : le réflexe de vérifier *avant* d'affirmer.
2. **Confusion entre concepts locaux et distants** (Git) — à retester plus tard (push/pull vs branches locales, staging vs commit).
3. Bonne honnêteté quand il ne sait vraiment pas ("je ne sais pas") plutôt que d'inventer — à préserver, c'est une qualité rare et précieuse pour l'apprentissage.

### Axes à travailler pour la prochaine séance
- Retester à froid (sans aide) : différence `.git` / `.gitignore`, rôle du staging, ce qu'un `push` fait vs ne fait pas.
- Vérifier la doc Spring Boot sur `ddl-auto` (valeur par défaut embarqué vs externe) — resté en suspens cette séance.
- Introduire : conflits de merge Git (simulation volontaire), `git log` en détail, `git clone`.
- Spring Boot : commencer à coder en autonomie réelle (pas juste expliquer) — proposer un petit ajout de fonctionnalité sur `Produit` ou `Livre` à réaliser seul, avec guidage minimal, pour valider le "80% autonome" annoncé.

## Niveau global (après 1 séance)

- **Git : débutant → notions de base acquises (niveau "junior encadré").** Capable d'exécuter le cycle add/commit/push/branch/merge avec supervision légère ; pas encore autonome sur la résolution de problèmes imprévus (conflits, erreurs complexes).
- **Spring Boot : intermédiaire théorique, à valider en pratique.** Bonne compréhension du "quoi" et du "pourquoi" quand guidé ; le "80% autonome" en écriture de code reste à prouver par une mise en situation réelle de code, pas encore testée.
- **Méthode de travail : point fort à noter** — bon raisonnement déductif sous guidage, honnêteté sur les lacunes, réflexe de vérification qui se développe (à consolider pour qu'il devienne systématique et spontané).
