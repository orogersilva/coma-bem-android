Release Process
===============

Release
-------

1. Atualize a versão no `app/build.gradle` em caso de qualquer atualização relevante na aplicação,
    tais como alterações internas e/ou públicas.

2. Atualize o `CHANGELOG.md` descrevendo as alterações internas e/ou públicas.

3. Comite as alterações com `git commit -am "Preparando versão X.Y.Z"`, substituindo 'X.Y.Z'
    com o número da nova versão.

4. Crie uma tag anotada com `git tag -a X.Y.Z -m "Versão X.Y.Z"`, substituindo 'X.Y.Z' com o número
    da nova versão.

5. Push (`git push`) e push tag (`git push --tags`).

6. :shipit: