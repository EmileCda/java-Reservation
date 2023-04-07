# TP Design-Pattern

# Réservation

Implémentez l’application suivante
## Préparation

1. Créer les classes Humain
1. Créer la classe Gare
   1. Celle-ci doit être un Singleton
   1. Créer une liste des destinations disponibles à partir de cette gare :
      1. Tamboujak pour 100€
      1. Rimonrebourg pour 55€
      1. Valverney pour 122€
      1. Castle-Bonsari pour 34€
1. Ajouter à l’humain une méthode d’instance permettant de réserver un voyage parmi ces
   destinations, en 1 ère classe ou en 2 nd , sans prendre en paramètre la gare
   1. Si la réservation est en 1 ère classe, augmenter le tarif de 50%
   1. Afficher la réservation, nom de la personne, destination et prix
1. Permettre la décoration de la réservation pour appliquer
   1. Soit une ou plusieurs réductions de 25%
   1. Soit un passage en première
   1. Soit les deux
1. Créer la classe Chien et, à l’aide d’un adaptateur, lui permettre de réserver lui aussi un voyage

## Utilisation

Dans le Main, afficher les réservations suivantes

1. Robert souhaite se rendre à Valverney en première classe
1. Jeanne souhaite se rendre à Rimonrebourg, en seconde classe et en utilisant 2 bons de
   réduction
1. Max le chien accompagne Jeanne sur sa route et dispose des mêmes réductions
1. Christophe souhaite se rendre à Tamboujak et dispose d’un bon de passage en première
