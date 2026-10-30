import { registerPlugin } from '@capacitor/core';
const Radio=registerPlugin('PulsoRadio');
let starting=null,started=false;
const state=()=>window.PulsoRuntime?.getState();
window.PulsoAndroid={background:document.hidden,syncing:false,
 async start(askNotifications=false){const data=state();if(!data?.code)return;if(starting)return starting;starting=(async()=>{const result=await Radio.start({...data,askNotifications});started=true;const label=document.getElementById('pushState');if(label)label.textContent=result.notifications?'Serviço Android ativo. Recebe voz e mensagens em segundo plano.':'Rádio ativo. Permita notificações para ver os alertas.';return result})().finally(()=>starting=null);return starting},
 async syncState(){const data=state();if(!data?.code)return;if(!data.enabled){if(started){await Radio.stop();started=false}return}await Radio.configure(data)},
 async importTone(kind,bytes){let text='';const data=new Uint8Array(bytes);for(let i=0;i<data.length;i+=8192)text+=String.fromCharCode(...data.subarray(i,i+8192));await Radio.importTone({kind,audio:btoa(text)})},
 async fullscreen(enabled){await Radio.fullscreen({enabled})},
 async flashSettings(enabled,askPermission=false){return Radio.flashSettings({enabled,askPermission})},async flash(){await Radio.flash()},async stopFlash(){await Radio.stopFlash()},
 async checkUpdate(force=false){try{const result=await Radio.checkUpdate({force});const el=document.getElementById('updateState');el.textContent=result.available?'Versão '+result.versionName+' disponível'+(result.downloaded?' · pronta para instalar':''):result.error||'Seu aplicativo está atualizado.';document.getElementById('installUpdate').hidden=!result.available;return result}catch(e){document.getElementById('updateState').textContent=e.message}},
 async installUpdate(){const b=document.getElementById('installUpdate');b.disabled=true;document.getElementById('updateState').textContent='Preparando atualização…';try{const r=await Radio.installUpdate();document.getElementById('updateState').textContent=r.permission?'Autorize esta fonte no Android e toque em instalar novamente.':'Confirme a instalação na tela do Android.'}catch(e){document.getElementById('updateState').textContent=e.message}finally{b.disabled=false}},
 async exportBackup(data){await Radio.exportBackup({data})},
 async stop(){await Radio.stop();started=false;window.PulsoRuntime?.setEnabled(false)},
 async batterySettings(){await Radio.batterySettings()},
 async download(id,name){await Radio.download({id,name});window.PulsoRuntime?.toast('Arquivo enviado para Downloads.')}
};
async function visibility(){const adapter=window.PulsoAndroid;adapter.background=document.hidden;if(!state()?.code||!started)return;if(adapter.syncing){adapter.resync=true;return}adapter.syncing=true;try{await adapter.syncState();const result=await Radio.foreground({visible:!document.hidden,cursor:state().cursor});if(!document.hidden){window.PulsoRuntime?.setCursor(result.cursor);if(result.enabled===false)window.PulsoRuntime?.setEnabled(false);for(const packet of result.backlog||[])await window.PulsoRuntime?.receive(packet)}}catch{}finally{adapter.syncing=false;if(adapter.resync){adapter.resync=false;visibility()}else if(!document.hidden)window.PulsoRuntime?.resume()}}
document.addEventListener('visibilitychange',visibility);window.addEventListener('focus',()=>{if(!document.hidden)visibility()});

window.addEventListener('DOMContentLoaded',()=>{window.PulsoAndroid.checkUpdate(false);window.PulsoAndroid.flashSettings(document.getElementById('flashEnabled')?.checked===true,false).catch(()=>{});window.PulsoAndroid.fullscreen(document.getElementById('fullscreenEnabled')?.checked!==false).catch(()=>{});const anchor=document.getElementById('radioPower');if(!anchor)return;const button=document.createElement('button');button.textContent='Ajustar bateria para segundo plano';button.onclick=()=>window.PulsoAndroid.batterySettings();anchor.after(button);const hint=document.createElement('p');hint.className='hint';hint.textContent='Nos ajustes do Android, permita bateria sem restrições para manter o recebimento do rádio.';button.after(hint)});
