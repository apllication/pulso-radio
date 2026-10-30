# Pulso — projeto para Android Studio

## Abrir

1. Extraia o ZIP inteiro em uma pasta local, de preferência com caminho curto, como `C:\Projetos\Pulso`.
2. Instale Node.js 22 ou superior e Android Studio com Android SDK 36.
3. Abra `Preparar-e-Abrir.cmd`. Ele instala as dependências, prepara a interface e abre o projeto Android.
4. Se preferir abrir manualmente: execute `npm ci` e `npm run android:sync` nesta pasta. No Android Studio, escolha **Open** e selecione a subpasta **android**.
5. Em Settings → Build, Execution, Deployment → Build Tools → Gradle, escolha **Gradle JDK 21**. Aguarde a sincronização.

## Gerar o APK

Para testar, use Build → Build Bundle(s) / APK(s) → Build APK(s). O APK de teste sai em `android/app/build/outputs/apk/debug/app-debug.apk`.

Para distribuir uma versão definitiva, use Build → Generate Signed App Bundle / APK → APK. Crie e guarde sua chave de assinatura fora do código: ela será necessária para atualizar o mesmo aplicativo no futuro. O projeto não inclui assinatura privada.

## O que está incluído

- Interface local com rádio 3D, chamada por código, menu, chat e arquivos.
- Servidor já configurado: https://pulso-radio-matheus.airycake256.chatgpt.site
- Serviço Android `RadioService` para receber voz e mensagens em segundo plano.
- Notificação permanente com opção de desligar o rádio.
- Toque de chamada fornecido pelo usuário e reprodução nativa dos pacotes de voz.
- Serviço com tipos `mediaPlayback` e `remoteMessaging`; o microfone da interface transmite apenas enquanto o botão estiver pressionado.
- Permissões de câmera, microfone, notificações, internet e serviço de primeiro plano declaradas.
- Download de arquivos pelo gerenciador do Android.
- Ícones e arquivos de interface já preparados, sem depender de arquivos fora desta pasta.

## Primeiro uso no celular

1. Abra o Pulso e permita microfone e notificações quando solicitados.
2. No menu, toque em **Permitir alertas do Android**.
3. Use **Ajustar bateria para segundo plano** para abrir as configurações do app. Permita uso de bateria sem restrições, se essa opção existir no aparelho.
4. Confira a notificação **Pulso • Rádio ativo** antes de sair do app. Ela indica que o serviço está ativo.
5. Para interromper o recebimento, use **Desligar rádio** na notificação.

Cada instalação tem armazenamento próprio. Os códigos e contatos do navegador não são importados automaticamente para o APK. Mantenha o app instalado para preservar seu código; limpar os dados ou desinstalar remove as credenciais locais.

O Android pode encerrar serviços por ação do usuário ou por restrições do fabricante. Não há promessa de execução após Forçar parada. Câmera e chamadas de vídeo contínuas ainda dependem da conexão direta entre os aparelhos.

## Editar

- `web-src/radio-client.js`: lógica da interface e rádio.
- `web-src/radio-template.html`: aparência e controles.
- `native-adapter.js`: integração com o serviço Android.
- `android/app/src/main/java/app/pulso/radio/`: serviço, plugin e atividade Android.
- Após editar: `npm run android:sync`.

## Validação realizada

O código Java do serviço e do plugin foi compilado contra Android SDK 36 e Capacitor. A sintaxe da interface foi verificada. A geração completa do APK neste ambiente parou porque a ferramenta AAPT2 encerrou durante a montagem dos recursos. Este pacote é o projeto-fonte, não um APK concluído.

Não havia celular conectado nem emulador configurado para validar o comportamento real com tela bloqueada. Teste em pelo menos dois celulares antes de distribuir a versão definitiva.

Documentação oficial: https://capacitorjs.com/docs/android

## Sons e botão de volume
No menu de três riscos, escolha sons de chamadas e mensagens. O rádio direto vem sem toque, com opção de ativar. Em Botão físico para falar, escolha Volume + ou Volume − e o modo TALK ou Rádio direto. No APK, com o app aberto na tela do rádio, segure o botão para falar e solte para parar. No modo TALK, a primeira pressão chama e aguarda aceite; depois pressione novamente para falar. O botão escolhido volta a controlar volume fora do app. Ainda é necessário testar os controles num aparelho real.


## Toques pessoais e foto
No menu Som e alertas, use Escolher toque do celular ou Escolher som do celular. Aceita áudio compatível com até 5 MB e toca os primeiros 10 segundos. Os arquivos ficam no aparelho; no navegador, alertas em segundo plano usam o som do sistema. Para incluir foto, abra Meu perfil e use Escolher minha foto. A foto reduzida é salva no servidor e fica visível aos contatos. É possível removê-la pelo mesmo menu.


## Correção de áudio 1.1.0
Esta versão remove o encerramento do TALK por 4 segundos. Corrige inicialização do serviço, retomada de áudio após permissões e cancelamento no toque prolongado. Instale o APK recompilado nos dois aparelhos. Após aceitar, segure TALK para transmitir e solte para parar. Se ficar sem som, toque Ativar áudio e use Som e alertas > Verificar áudio; informe Som, Microfone, Voz enviada e Recebida. O controle de volume de reprodução é o de mídia. A validação foi feita por testes de lógica e compilação Java, sem aparelho físico conectado.


## Contatos e tela cheia 1.1.1
Use Contatos salvos na tela principal ou Abrir lista de contatos no menu para buscar, selecionar, editar ou excluir. O contato único da versão anterior é migrado automaticamente. A lista fica no armazenamento deste aparelho, sem sincronização entre dispositivos. Tela cheia no APK fica em Tela e instalação e é salva por aparelho; deslize da borda para revelar as barras do Android. O layout se adapta a retrato e paisagem. Ainda é necessário validar recortes de tela e navegação no aparelho real.

