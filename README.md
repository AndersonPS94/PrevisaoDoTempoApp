# 📱 Previsão do tempo - Devspace desafio Intermediario

>tem como principal funcionalidade, exibir a previsão do tempo de São Paulo em tempo real

---

## 📸 Demonstração  

<img src="https://github.com/user-attachments/assets/bbfd43b7-d2c9-443c-b33c-ea2b66204478" width="230">
&nbsp;&nbsp;

<img src="https://github.com/user-attachments/assets/29d31e69-9799-4d46-8281-f7034f3f9584" width="230">
&nbsp;&nbsp;

<img src="https://github.com/user-attachments/assets/76df115e-fcab-4096-a077-9ba563912856" width="230">
&nbsp;&nbsp;

<img src="https://github.com/user-attachments/assets/aa81279c-def1-4906-a496-628d2b92c8d7" width="230">
&nbsp;&nbsp;







---

## 🛠️ Tecnologias Utilizadas  
Este projeto foi desenvolvido com as seguintes tecnologias:  

✅ **Kotlin**  
✅ **MVVM**  
✅ **Retrofit**  
✅ **XML Layouts**  
✅ **Open Meteo API**      
✅ **Injeção de dependências com o Hilt**
---

## 📂 Estrutura do Projeto
O código segue o padrão MVVM:

```plaintext
📂 app                   
├── 📂 data             
│   ├── 📂 Remote                        
│   │   ├── 📂 api                 
│   │   │   ├── RetrofitInstance.kt                                  
│   │   │   ├── WeatherService.kt                               
│   │   ├── 📂 repository                     
│   │   │   ├── IWeatherRepository.kt                 
│   │   │   ├── WeatherRepositoryImpl.kt                
│   ├── 📂 di                    
│   │   ├── AppModule.kt                 
│   │   ├── BaseApplication.kt              
├── 📂 domain                
│   ├── 📂 model                
│   │   ├── WeatherResponse.kt          
├── 📂 presentation
│   ├── 📂 ui
│   │   ├── 📂 activity/view
│   │   │   ├── CityDetailsActivity.kt
│   │   │   ├── MainActivity.kt
│   │   ├── 📂 adapters 
│   │   │   ├── HourlyAdapter.kt
│   │   │   ├── ViewPagerAdapter.kt
│   │   │   ├── WeeklyAdapter.kt
│   │   ├── 📂 fragments 
│   │   │   ├── NextDaysFragment.kt
│   │   │   ├── TodayFragment.kt
│   │   │   ├── TomorrowFragment.kt
│   ├── 📂 viewmodel
│   │   ├── WeatherViewModel.kt
```


## 📄 Aprendizados
Durante o desenvolvimento deste projeto, aprendi sobre:

✅ Consumo de APIs REST com Retrofit.        
✅ Implementação do padrão MVVM.        
✅ Manipulação de LiveData e ViewModel.        
✅ Injeção de dependências com Hilt.      

---

## 🚀 Como Executar  

Para rodar o projeto na sua máquina, siga estes passos:  

```bash
# Clone este repositório
git clone https://github.com/AndersonPS94/PrevisaoDoTempoApp.git

# Acesse a pasta do projeto no terminal
cd PrevisaoDoTempoApp

# Abra o projeto no Android Studio e execute no emulador ou dispositivo real.
```


## 📜 Licença
```
The MIT License (MIT)

Copyright (c) 2024 Anderson Pereira dos Santos

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
