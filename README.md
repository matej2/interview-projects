# About

This repository includes projects that were part of interview process. Each project can be ran in a separte environment, check readme documents.

## Adding existing projects

To add existing project to this app, we need to first add and fetch from local remote:

    git remote add football-java ~/Projekti/LiveFootballWorldCup-java/

    git fetch 

After this, we need to create local branches and set track to remote:

    git checkout -b football-java --track football-java/master

Now we have all reqired (but unrelated) git histories locally. We need to merge those histories:

    git merge football-java --allow-unrelated-histories