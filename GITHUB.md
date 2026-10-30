# Pulso Android

Projeto Android do Pulso. O servidor de rádio permanece em https://pulso-radio-matheus.airycake256.chatgpt.site/.

## Instalar

Baixe **Pulso.apk** na seção [Releases](https://github.com/apllication/pulso-radio/releases/latest).

## Atualizações

O APK consulta novas versões ao abrir e periodicamente, com intervalo de seis horas. Baixa automaticamente quando a conexão não é medida (normalmente Wi-Fi), verifica o resumo SHA-256 e exige a mesma assinatura do aplicativo instalado. A instalação precisa de confirmação no Android. O botão Verificar atualização agora permite consultar manualmente.

As versões são geradas por GitHub Actions ao atualizar main. A chave fica apenas no segredo PULSO_SIGNING_JSON; nenhum arquivo de assinatura deve ser enviado ao repositório. Para um fork, crie sua própria chave e configure seu próprio segredo.

## Primeira versão assinada

O APK de teste anterior usa outra assinatura. Ele não pode ser substituído por esta versão preservando automaticamente os dados. Não desinstale sem preservar seu ID e contatos. A versão nova oferece backup e restauração em Tela e instalação para futuras migrações. Depois da primeira instalação com a assinatura fixa, as atualizações seguintes mantêm os dados.

## Flash e interface

Ative Flash ao receber chamada no menu e autorize a câmera no aparelho. O flash pisca por um período curto e é desligado ao atender/recusar. O comportamento precisa ser testado em um aparelho físico; pode não funcionar quando a câmera estiver ocupada.

Mensagens são acessadas pelo balão no cabeçalho. O layout inicial é compacto e usa a altura disponível; mensagens e opções têm rolagem própria. Contatos e preferências ficam salvos no aparelho.

Java 21, Node 22, Android SDK 36. Abra a pasta android no Android Studio ou leia LEIA-ME.md.
