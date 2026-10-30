# Pulso no GitHub

Este pacote contém o projeto Android independente e uma tarefa manual para gerar um APK de teste no GitHub Actions.

1. Coloque o conteúdo desta pasta na raiz do repositório, incluindo a pasta oculta `.github`.
2. Abra **Actions → Gerar APK de teste → Run workflow**.
3. Quando a execução terminar com sucesso, abra **Artifacts → Pulso-APK-teste**.
4. Baixe e extraia o ZIP para encontrar `app-debug.apk`.

A tarefa ainda não foi executada no GitHub. Ela usa Node 22, Java 21 e Android SDK 36.

Este é um APK de teste. Para atualizar regularmente a instalação existente preservando dados, configure uma chave de assinatura fixa; o certificado de teste gerado pelo executor pode ser diferente do usado no seu celular. Não desinstale a versão atual antes de resolver a assinatura.

O servidor de rádio continua hospedado no endereço configurado no projeto. Este repositório Android não substitui o servidor.
