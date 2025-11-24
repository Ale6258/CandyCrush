public class CandyCrush {

    /**
     * Construit une configuration initiale de plateau de jeu n x n.
     *
     * Pour chaque case, la fonction choisit aléatoirement un caractère
     * parmi la liste de bonbons {'C', 'A', 'N', 'D', 'Y'}.
     *
     * @param n taille du plateau (nombre de lignes et de colonnes).
     *
     * @return un tableau 2D de caractères (char[n][n]) représentant
     *         le plateau de jeu initial, rempli aléatoirement.
     *
     * Complexité temporelle :
     * - Deux boucles imbriquées sur n lignes et n colonnes : O(n * n) = O(n²).
     */
    public static char[][] construireJeux(int n) {
        char[] liste_candy = {'C', 'A', 'N', 'D', 'Y'};
        char[][] plateau_jeux = new char[n][n]; // taille n x n pour le jeu

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { // parcourt tous les indices du tableau
                // choix d'un indice aléatoire entre 0 et liste_candy.length - 1
                int random_index = (int)(Math.random() * liste_candy.length);
                // affectation du caractère correspondant à cet indice
                plateau_jeux[i][j] = liste_candy[random_index];
            }
        }
        return plateau_jeux;
    }

    /**
     * Affiche dans le terminal le plateau de jeu sous forme de grille,
     * avec les indices de ligne et de colonne visibles.
     *
     * Hypothèse : le tableau est carré (même nombre de lignes et de colonnes).
     *
     * @param jeux le tableau 2D de caractères représentant le plateau de jeu.
     *             jeux.length = nombre de lignes = nombre de colonnes.
     *
     * Complexité temporelle :
     * - On parcourt toutes les cases du plateau une fois pour les afficher : O(n²).
     */
    public static void afficherJeux(char[][] jeux) {
        int n = jeux.length;

        // Affichage des indices de colonnes (1..n pour coller à l’énoncé)
        Ecran.afficher("    ");  // marge pour aligner avec les indices de ligne
        for (int j = 1; j <= n; j++) {
            Ecran.afficher(j + " ");
        }
        Ecran.afficherln("");

        // Ligne de séparation horizontale
        Ecran.afficher("   +");
        for (int j = 0; j < n; j++) {
            Ecran.afficher("- ");
        }
        Ecran.afficherln("+");

        // Affichage de chaque ligne du plateau avec son indice (1..n)
        for (int i = 0; i < n; i++) {
            Ecran.afficher((i + 1) + " | ");  // indice de ligne + séparateur
            for (int j = 0; j < n; j++) {
                Ecran.afficher(jeux[i][j] + " ");
            }
            Ecran.afficherln("|");
        }

        // Ligne de séparation du bas
        Ecran.afficher("   +");
        for (int j = 0; j < n; j++) {
            Ecran.afficher("- ");
        }
        Ecran.afficherln("+");
    }

    /**
     * Demande à l'utilisateur de saisir les coordonnées de deux éléments à permuter,
     * sur un plateau de taille n x n.
     *
     * Conditions :
     * - Les indices doivent être compris entre 0 et n-1.
     * - Les deux cases doivent être adjacentes (même ligne et colonnes qui diffèrent de 1,
     *   ou même colonne et lignes qui diffèrent de 1).
     *
     * Tant que ces conditions ne sont pas respectées, la saisie est redemandée avec
     * un message d'erreur.
     *
     * @param n taille du plateau (indices valides 0..n-1).
     *
     * @return un tableau d'entiers de taille 4 : {x1, y1, x2, y2}, où
     *         (x1, y1) et (x2, y2) sont des cases adjacentes du plateau.
     *         (théoriquement, ne retourne jamais null car la boucle ne se termine
     *         qu'avec des coordonnées valides).
     *
     * Complexité temporelle :
     * - Chaque vérification de coordonnées est O(1).
     * - La boucle while dépend du nombre d'essais de l'utilisateur, mais pour
     *   une saisie correcte, on considère la complexité moyenne O(1).
     */
    public static int[] choixPermutation(int n) {
        boolean test = false; // indique si la saisie est valide ou non
        while (!test) {
            Ecran.afficherln("Entrez la ligne du premier élément à permuter (1-" + n + ") : ");
            int x1 = Clavier.saisirInt() - 1; // conversion en indice 0..n-1
            Ecran.afficherln("Entrez la colonne du premier élément à permuter (1-" + n + ") : ");
            int y1 = Clavier.saisirInt() - 1;
            Ecran.afficherln("Entrez la ligne du deuxieme élément à permuter (1-" + n + ") : ");
            int x2 = Clavier.saisirInt() - 1;
            Ecran.afficherln("Entrez la colonne du deuxieme élément à permuter (1-" + n + ") : ");
            int y2 = Clavier.saisirInt() - 1;

            // Vérification des bornes et de l'adjacence
            if ((x1 >= 0 && x1 < n) && (x2 >= 0 && x2 < n)
                && (y1 >= 0 && y1 < n) && (y2 >= 0 && y2 < n)
                && ((x1 == x2 && Math.abs(y1 - y2) == 1)
                    || (y1 == y2 && Math.abs(x1 - x2) == 1))) {

                test = true; // saisie valide
                return new int[] { x1, y1, x2, y2 };
            } else {
                Ecran.afficherln("Les coordonnées ne sont pas adjacentes et/ou ne respectent pas les bornes. Veuillez réessayer.");
            }
        }
        // Théoriquement jamais atteint, mais nécessaire pour la compilation Java.
        return null;
    }

    /**
     * Permute le contenu de deux cases du plateau.
     *
     * @param x1   indice de ligne de la première case (0..n-1)
     * @param y1   indice de colonne de la première case (0..n-1)
     * @param x2   indice de ligne de la seconde case (0..n-1)
     * @param y2   indice de colonne de la seconde case (0..n-1)
     * @param jeux le plateau de jeu (char[n][n]) sur lequel effectuer la permutation.
     *
     * @return le même tableau 'jeux', après permutation des deux cases.
     *
     * Complexité temporelle :
     * - Un simple échange de deux éléments : O(1).
     */
    public static char[][] permutation(int x1, int y1, int x2, int y2, char[][] jeux) {
        char temporaire = jeux[x1][y1];
        jeux[x1][y1] = jeux[x2][y2]; // inversion des deux caractères
        jeux[x2][y2] = temporaire;
        return jeux;
    }

    /**
     * Recherche tous les motifs (lignes ou colonnes) contenant au moins 3
     * mêmes caractères consécutifs et "supprime" ces bonbons en les remplaçant
     * par '-'.
     *
     * La fonction procède en deux temps :
     * 1. Détecter les motifs horizontalement (par ligne) et verticalement (par colonne)
     *    et les noter dans un tableau de booléens 'motif'.
     * 2. Remplacer dans le plateau tous les éléments marqués par '-' (suppression).
     *
     * @param jeux le plateau de jeu (char[n][n]).
     *
     * @return le même tableau 'jeux', avec les bonbons appartenant à un motif
     *         de longueur ≥ 3 remplacés par '-'.
     *
     * Complexité temporelle :
     * - Parcours horizontal : O(n * (n-2)) ≈ O(n²)
     * - Parcours vertical : O(n * (n-2)) ≈ O(n²)
     * - Parcours final de remplacement : O(n²)
     * ⇒ Complexité globale : O(n²).
     */
    public static char[][] rechercheMotif(char[][] jeux){

        int n = jeux.length;

        // 'motif[i][j] == true' signifie que la case (i, j) doit être supprimée.
        boolean[][] motif = new boolean[n][n];

        // Initialisation de toutes les cases à false (aucune suppression au départ)
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                motif[i][j] = false;
            }
        }

        // Recherche de motifs horizontaux (sur chaque ligne)
        // On balaye chaque ligne avec une fenêtre de longueur 3 : (colonne, colonne+1, colonne+2)
        for (int ligne = 0; ligne < n; ligne++){
            for (int colonne = 0; colonne <= n - 3; colonne++){
                if (jeux[ligne][colonne] == jeux[ligne][colonne+1]
                    && jeux[ligne][colonne+1] == jeux[ligne][colonne+2]) {

                    // On marque les trois cases comme appartenant à un motif
                    motif[ligne][colonne]   = true;
                    motif[ligne][colonne+1] = true;
                    motif[ligne][colonne+2] = true;
                }
            }
        }

        // Recherche de motifs verticaux (sur chaque colonne)
        // Même principe, mais on balaye verticalement.
        for (int colonne = 0; colonne < n; colonne++){
            for (int ligne = 0; ligne <= n - 3; ligne++){
                if (jeux[ligne][colonne] == jeux[ligne+1][colonne]
                    && jeux[ligne+1][colonne] == jeux[ligne+2][colonne]) {

                    motif[ligne][colonne]   = true;
                    motif[ligne+1][colonne] = true;
                    motif[ligne+2][colonne] = true;
                }
            }
        }

        // Suppression effective : on remplace par '-' toutes les cases marquées dans 'motif'
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (motif[i][j]) {
                    jeux[i][j] = '-';
                }
            }
        }

        return jeux;
    }

    /**
     * Génère un petit plateau de test 5x5 pour vérifier le bon comportement
     * des fonctions de suppression de motifs, de chute, etc.
     *
     * Remarque : ce plateau ne correspond pas forcément à la taille n x n utilisée
     * dans le programme principal, il est donc plutôt destiné à des tests
     * unitaires / locaux indépendants de la valeur de n.
     *
     * @return un plateau 5x5 prédéfini pour des tests.
     *
     * Complexité temporelle :
     * - Simple initialisation littérale d’un tableau : O(1).
     */
    public static char[][] test(){
        char[][] test = {
            {'B','A','C','C','C'},
            {'A','A','B','A','A'},
            {'C','A','B','A','A'},
            {'A','A','A','C','A'},
            {'B','A','A','A','A'}
        };
        return test;
    }

    /**
     * Fait "tomber" les bonbons dans chaque colonne du plateau.
     *
     * Principe :
     * - On parcourt chaque colonne de bas en haut.
     * - On maintient un indice 'position' qui représente la prochaine case
     *   (en partant du bas) où placer un bonbon réel.
     * - À chaque bonbon non vide ('-' signifie vide), on le "descend" à
     *   l'indice 'position', puis on décrémente 'position'.
     * - Une fois tous les bonbons d'une colonne descendus, on remplit le
     *   reste en haut par '-' (cases vides).
     *
     * @param jeux le plateau de jeu (char[n][n]) après suppression de motifs.
     *
     * @return le même tableau 'jeux', où les bonbons ont chuté vers le bas.
     *
     * Complexité temporelle :
     * - Pour chaque colonne, on parcourt les n lignes : O(n)
     * - On a n colonnes, donc O(n²).
     */
    public static char[][] chute(char[][] jeux){
        int n = jeux.length;

        for (int colonne = 0; colonne < n; colonne++) { // parcours de toutes les colonnes
            int position = n - 1; // index de la prochaine case "libre" en partant du bas

            // Parcours de la colonne de bas en haut
            for (int ligne = n - 1; ligne >= 0; ligne--) {
                if (jeux[ligne][colonne] != '-') { // on garde seulement les "vrais" bonbons
                    jeux[position][colonne] = jeux[ligne][colonne]; // on les fait tomber
                    position--; // la prochaine position libre remonte d'une ligne
                }
            }

            // Remplit le reste de la colonne, au-dessus de 'position', par des '-'
            for (int ligne = position; ligne >= 0; ligne--) {
                jeux[ligne][colonne] = '-';
            }
        }
        return jeux;
    }

    /**
     * Remplit les cases vides (marquées par '-') du plateau avec de nouveaux bonbons
     * choisis aléatoirement, en essayant d'éviter de créer immédiatement des motifs
     * de 3 bonbons identiques (horizontalement ou verticalement).
     *
     * Pour chaque case vide :
     * - On choisit aléatoirement un bonbon dans {'C', 'A', 'N', 'D', 'Y'}.
     * - On vérifie si ce choix créerait un motif horizontal (2 bonbons identiques
     *   à gauche) ou vertical (2 bonbons identiques au-dessus).
     * - Si oui, on recommence avec un nouveau choix.
     * - On limite le nombre d'essais à 50 pour éviter une boucle infinie.
     *
     * @param jeux le plateau de jeu (char[n][n]) avec des '-'
     *             représentant les cases vides.
     *
     * @return le même tableau 'jeux', où toutes les cases vides ont été remplies.
     *
     * Complexité temporelle :
     * - Parcours de toutes les cases : O(n²).
     * - Pour chaque case, on fait au plus un nombre constant d'essais (limité à 50),
     *   donc le coût par case reste O(1) en pratique.
     * ⇒ Complexité globale : O(n²).
     */
    public static char[][] remplissage(char[][] jeux){
        int n = jeux.length;
        char[] candy = {'C', 'A', 'N', 'D', 'Y'};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { // parcours de tout le plateau

                if (jeux[i][j] == '-') { // action seulement s'il faut remplir la case

                    char choisi;
                    boolean ok;
                    int essais = 0;

                    do {
                        int randomIndex = (int)(Math.random() * candy.length);
                        choisi = candy[randomIndex]; // choix aléatoire de bonbon
                        ok = true;

                        // Vérifie si on crée un motif horizontal de trois
                        if (j >= 2 && jeux[i][j-1] == choisi && jeux[i][j-2] == choisi) {
                            ok = false;
                        }

                        // Vérifie si on crée un motif vertical de trois
                        if (i >= 2 && jeux[i-1][j] == choisi && jeux[i-2][j] == choisi) {
                            ok = false;
                        }

                        essais++;

                        // Si ok == false, on recommence avec un autre choix
                    } while (!ok && essais < 50); // sécurité pour éviter une boucle infinie

                    jeux[i][j] = choisi; // affectation du bonbon choisi
                }
            }
        }

        return jeux;
    }

    /**
     * Crée une copie indépendante d'un plateau n x n.
     *
     * Utile pour éviter de modifier directement le plateau de référence
     * (par exemple lors d’une simulation de coup).
     *
     * @param tableau le plateau original à copier (char[n][n]).
     *
     * @return un nouveau tableau n x n contenant les mêmes caractères.
     *
     * Complexité temporelle :
     * - Parcours et copie de toutes les cases : O(n²).
     */
    public static char[][] copieTableau(char[][] tableau){
        int n = tableau.length;
        char[][] tableau_copie = new char[n][n];
        for (int i = 0; i < n; i++){
            for (int j  = 0; j < n; j++){
                tableau_copie[i][j] = tableau[i][j]; // copie élément par élément
            }
        }
        return tableau_copie;
    }

    /**
     * Compare deux tableaux n x n et indique s'ils sont identiques ou non.
     *
     * @param copie   premier plateau (char[n][n]).
     * @param plateau second plateau (char[n][n]).
     *
     * @return true s'il existe au moins une case (i, j) telle que
     *         copie[i][j] != plateau[i][j], false sinon (plateaux identiques).
     *
     * Complexité temporelle :
     * - Parcours de toutes les cases des deux tableaux : O(n²).
     */
    public static boolean differents(char[][] copie, char[][] plateau){
        int n = plateau.length;
        boolean test = false;
        for (int i = 0; i < n; i++){
            for (int j  = 0; j < n; j++){
                if (copie[i][j] != plateau[i][j]){ // dès qu'une différence est trouvée
                    test = true;
                    break; // on sort de la boucle interne (mais on pourrait sortir plus tôt)
                }
            }
        }
        return test;
    }

    /**
     * Demande à l'utilisateur s'il souhaite continuer à jouer ou quitter le jeu.
     *
     * - Si l'utilisateur répond 'o' ou 'O' : la fonction retourne simplement et le jeu continue.
     * - Sinon : un message de remerciement s'affiche et le programme se termine avec System.exit(0).
     *
     * @implNote Cette méthode ne retourne pas d'information explicite, mais
     *           peut interrompre définitivement le programme.
     *
     * Complexité temporelle :
     * - Lecture d'un caractère et comparaison : O(1).
     */
    public static void sortie(){
        Ecran.afficherln("Voulez-vous continuer à jouer ? (o/n)");
        char reponse = Clavier.saisirChar(); // entrée de l'utilisateur
        if (reponse == 'o' || reponse == 'O'){
            return; // le jeu continue
        } else {
            Ecran.afficherln("Merci d'avoir joué !");
            System.exit(0); // arrêt du jeu
        }
    }

    /**
     * Boucle principale de gestion du plateau.
     *
     * Deux modes de fonctionnement :
     * - Mode joueur (auto == false) :
     *      * stabilise le plateau (motifs -> chute -> remplissage),
     *      * propose un meilleur coup,
     *      * demande à l'utilisateur s'il veut continuer,
     *      * lit une permutation au clavier,
     *      * relance le jeu.
     *
     * - Mode bot (auto == true) :
     *      * stabilise le plateau,
     *      * calcule le meilleur coup,
     *      * si aucun coup possible → fin du mode bot,
     *      * sinon applique automatiquement ce meilleur coup,
     *      * relance le jeu sans intervention de l'utilisateur.
     *
     * @param plateau plateau de jeu (char[n][n]).
     * @param auto    true si le jeu doit être joué automatiquement par le bot,
     *                false si c'est le joueur humain qui choisit les coups.
     *
     * Complexité temporelle (ordre de grandeur) :
     * - À chaque "cycle" de stabilisation :
     *   * rechercheMotif : O(n²)
     *   * chute : O(n²)
     *   * remplissage : O(n²)
     * - Ajout du calcul du meilleur coup : O(n⁵) (voir meilleurCoup / simulerCoup).
     */
    public static void jeux(char[][] plateau, boolean auto){
        int n = plateau.length;
        char[][] plateau_copie = new char[n][n];

        // Tant que le plateau continue de changer après suppression / chute / remplissage
        while (differents(plateau_copie, plateau)) {
            plateau_copie = copieTableau(plateau);

            Ecran.afficherln("Voici le plateau");
            afficherJeux(plateau);
            timeSlep(500); // petite pause pour laisser le temps de voir

            plateau = rechercheMotif(plateau);
            Ecran.afficherln("Plateau sans motif de + de 3 de long");
            afficherJeux(plateau);
            timeSlep(500);

            plateau = chute(plateau);
            Ecran.afficherln("Chute");
            afficherJeux(plateau);
            timeSlep(500);

            plateau = remplissage(plateau);
            Ecran.afficherln("Remplissage du plateau");
            afficherJeux(plateau);
            timeSlep(500);
        }

        // Suggestion de meilleur coup une fois le plateau stabilisé
        int[] suggestion = meilleurCoup(plateau);
        if (suggestion[0] != -1) {
            Ecran.afficherln(">>> MEILLEUR COUP PROPOSÉ : (" +
                (suggestion[0] + 1) + "," + (suggestion[1] + 1) + ") <-> (" +
                (suggestion[2] + 1) + "," + (suggestion[3] + 1) + ")");
        } else {
            Ecran.afficherln("Aucun coup intéressant détecté.");
        }

        timeSlep(500);

        if (auto) {
            // MODE BOT : joue automatiquement le meilleur coup jusqu'à ce qu'il n'y ait plus de coup
            if (suggestion[0] == -1) {
                Ecran.afficherln("Plus aucun coup possible, fin du mode automatique.");
                return;
            }
            Ecran.afficherln("Le bot joue ce coup automatiquement...");
            permutation(suggestion[0], suggestion[1], suggestion[2], suggestion[3], plateau);
            timeSlep(500);
            jeux(plateau, true); // relance en mode auto
        } else {
            // MODE JOUEUR : on laisse la main à l'utilisateur
            sortie(); // demande si on continue
            int[] coords = choixPermutation(plateau.length);
            permutation(coords[0], coords[1], coords[2], coords[3], plateau);
            jeux(plateau, false); // relance en mode joueur
        }
    }

    /**
     * Simule un coup (permutation de deux cases adjacentes) et calcule le score
     * obtenu par ce coup, en prenant en compte les effets de cascade :
     *
     * - On travaille sur une copie du plateau pour ne pas modifier l’original.
     * - On applique la permutation.
     * - Tant qu'il existe des motifs à supprimer :
     *   1. On appelle rechercheMotif pour marquer les motifs et les supprimer.
     *   2. On compte le nombre de bonbons supprimés (suppr) entre l'état "avant"
     *      et l'état "après".
     *   3. On ajoute ce nombre au score.
     *   4. On applique chute puis remplissage pour poursuivre la cascade.
     * - Dès qu'aucun bonbon n'est supprimé (suppr == 0), la cascade est terminée
     *   et on retourne le score total.
     *
     * @param x1      ligne de la première case (0..n-1)
     * @param y1      colonne de la première case (0..n-1)
     * @param x2      ligne de la deuxième case (0..n-1)
     * @param y2      colonne de la deuxième case (0..n-1)
     * @param plateau le plateau de jeu initial (char[n][n]) avant le coup.
     *
     * @return le score total obtenu par ce coup (nombre de bonbons supprimés
     *         sur l’ensemble de la cascade).
     *
     * Complexité temporelle (pire cas théorique) :
     * - À chaque itération de la boucle while :
     *   * rechercheMotif : O(n²)
     *   * comptage des suppressions : O(n²)
     *   * chute : O(n²)
     *   * remplissage : O(n²)
     *   ⇒ chaque cycle de cascade : O(n²).
     * - Nombre maximal d'itérations de la cascade borné (au plus proportionnel à n),
     *   d'où une majoration théorique en O(n³).
     */
    public static int simulerCoup(int x1, int y1, int x2, int y2, char[][] plateau) {

        int n = plateau.length;

        // copie du plateau pour ne rien modifier
        char[][] temp = copieTableau(plateau);

        // application de la permutation sur la copie
        permutation(x1, y1, x2, y2, temp);

        int score = 0;

        // On répète jusqu'à ce qu'il n'y ait plus de bonbons supprimés
        while (true) {
            char[][] avant = copieTableau(temp);
            temp = rechercheMotif(temp);

            int suppr = 0;

            // comptage des bonbons supprimés à cette étape
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // un bonbon supprimé est un élément qui était différent de '-'
                    // et qui devient '-'
                    if (avant[i][j] != '-' && temp[i][j] == '-') {
                        suppr++;
                    }
                }
            }

            // s'il n'y a plus de bonbons supprimés, la cascade est terminée
            if (suppr == 0) break;

            // ajout des suppressions de cette étape au score total
            score += suppr;

            // On applique la chute et le remplissage pour poursuivre la cascade
            temp = chute(temp);
            temp = remplissage(temp);
        }

        return score;
    }

    /**
     * Cherche le meilleur coup possible sur le plateau, c'est-à-dire la permutation
     * de deux cases adjacentes qui maximise le score obtenu (d'après simulerCoup).
     *
     * Pour chaque case (i, j) :
     * - On simule la permutation avec son voisin de droite (i, j+1), si la case existe.
     * - On simule la permutation avec son voisin du bas (i+1, j), si la case existe.
     * - Pour chaque coup simulé, on calcule le score et on conserve les coordonnées
     *   de celui qui donne le score maximal.
     *
     * @param plateau le plateau de jeu (char[n][n]).
     *
     * @return un tableau d'entiers {x1, y1, x2, y2} représentant le meilleur coup,
     *         ou {-1, -1, -1, -1} s'il n'existe aucun coup améliorant la situation
     *         (par exemple si aucun motif ne peut être créé).
     *
     * Complexité temporelle (pire cas théorique) :
     * - On parcourt chaque case du plateau : O(n²).
     * - Pour chaque case, on peut simuler jusqu'à 2 coups (droite et bas).
     * - Chaque simulation de coup (simulerCoup) coûte O(n³) en pire cas théorique.
     * ⇒ Complexité globale : O(n² * n³) = O(n⁵).
     */
    public static int[] meilleurCoup(char[][] plateau) {

        int n = plateau.length;

        int meilleurScore = 0;
        int[] meilleur = { -1, -1, -1, -1 };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Coup avec le voisin de droite
                if (j + 1 < n) {
                    int score = simulerCoup(i, j, i, j + 1, plateau);
                    if (score > meilleurScore) {
                        meilleurScore = score;
                        meilleur = new int[]{i, j, i, j + 1};
                    }
                }

                // Coup avec le voisin du bas
                if (i + 1 < n) {
                    int score = simulerCoup(i, j, i + 1, j, plateau);
                    if (score > meilleurScore) {
                        meilleurScore = score;
                        meilleur = new int[]{i, j, i + 1, j};
                    }
                }
            }
        }

        return meilleur;
    }

    /**
     * Met le thread actuel en pause pendant un certain nombre de millisecondes.
     *
     * @param ms durée de la pause en millisecondes.
     *
     * En cas d'interruption, l'état d'interruption du thread est restauré.
     *
     * Complexité temporelle :
     * - L'appel à Thread.sleep(ms) est conceptuellement O(1) du point de vue
     *   algorithmique (on ne fait pas de travail proportionnel à n).
     */
    public static void timeSlep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Mesure expérimentalement le temps d'exécution moyen de meilleurCoup(plateau)
     * pour une taille de plateau donnée n, sur un certain nombre d'essais.
     *
     * @param n      taille du plateau (n x n).
     * @param essais nombre d'essais à effectuer pour la moyenne (>= 1).
     *
     * @return temps moyen d'exécution en nanosecondes.
     *
     * Complexité temporelle :
     * - Appel interne à meilleurCoup : O(n⁵) en théorie.
     * - Répété 'essais' fois → O(essais * n⁵).
     *   Comme 'essais' est constant et petit (3 à 5), cela reste raisonnable.
     */
    public static long analyserComplexite(int n, int essais) {
        long total = 0;

        for (int k = 0; k < essais; k++) {
            char[][] plateau = construireJeux(n);
            long debut = System.nanoTime();

            meilleurCoup(plateau);

            long fin = System.nanoTime();
            total += (fin - debut);
        }

        return total / essais; // temps moyen
    }

    /**
     * Effectue une analyse expérimentale de la complexité de la fonction
     * meilleurCoup() pour différentes tailles de plateau.
     *
     * Elle mesure le temps d'exécution moyen de meilleurCoup(n x n)
     * sur 3 essais pour chaque taille, puis affiche les résultats.
     *
     * But : comparer expérimentalement avec la complexité théorique O(n⁵)
     * en traçant un graphique (Excel / Google Sheets).
     *
     * Complexité temporelle :
     *  → Mesure expérimentale sur n ∈ tailles[]
     *  → Appel interne à meilleurCoup → O(n⁵)
     *  → Total = O(k · n⁵) où k = nombre d’essais (petit et constant).
     */
    public static void analyseComplexite() {
        int essais = 100;
        int[] tailles = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}; // tailles à tester

        Ecran.afficherln("=== Analyse expérimentale de la complexité de meilleurCoup ===");
        Ecran.afficherln("Taille (n)   Temps moyen (nanosecondes)");

        for (int n : tailles) {
            long temps = analyserComplexite(n, essais);
            Ecran.afficherln(n + " x " + n + "     ->     " + temps + " ns");
        }

        Ecran.afficherln("\nCes résultats peuvent être copiés dans Excel / Google Sheets pour tracer un graphique.");
        Ecran.afficherln("On pourra comparer la courbe obtenue à une croissance théorique en n⁵.");
    }

    /**
     * Point d'entrée du programme.
     *
     * Étapes :
     * 1. Demande à l'utilisateur la taille n du plateau (n x n), avec n ≥ 3.
     * 2. Demande le mode de jeu :
     *      - 1 : joueur humain (choix des coups au clavier),
     *      - 2 : bot automatique (joue à chaque tour le meilleur coup).
     * 3. Génère un plateau de jeu initial aléatoire via construireJeux(n).
     * 4. Lance la logique principale du jeu via jeux(plateau, auto).
     *
     * @param args arguments de la ligne de commande (non utilisés ici).
     *
     * Complexité temporelle :
     * - Dominée par l'appel à jeux(plateau, auto), lui-même dominé par les
     *   appels à meilleurCoup et simulerCoup (ordre O(n⁵) théorique).
     */
    public static void main(String[] args) {
        // 1) Demander si analyse complexe avant le jeu
        Ecran.afficherln("Souhaitez-vous lancer une analyse de complexité avant de jouer ? (o/n)");
        char rep = Clavier.saisirChar();

        if (rep == 'o' || rep == 'O') {
            analyseComplexite(); //analyse avant le jeu
        }

        // 2) Demande ensuite la taille du plateau
        int n;
        do {
            Ecran.afficherln("Entrez la taille du plateau (n pour un plateau n x n, n >= 3) : ");
            n = Clavier.saisirInt();
        } while (n < 3);

        // 3) Choix du mode
        Ecran.afficherln("Choisissez le mode de jeu :");
        Ecran.afficherln("1 - Joueur humain");
        Ecran.afficherln("2 - Bot automatique (meilleur coup)");
        int choixMode;
        do {
            choixMode = Clavier.saisirInt();
        } while (choixMode != 1 && choixMode != 2);

        char[][] plateau = construireJeux(n);
        boolean auto = (choixMode == 2);
        jeux(plateau, auto);
}

}
