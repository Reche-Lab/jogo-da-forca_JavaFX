# 🎯 Jogo da Forca com JavaFX

Este é um projeto de **Jogo da Forca** desenvolvido com **Java + JavaFX**, criado com fins **educacionais** e também como uma forma divertida de **entreter e ensinar minha filha**, que está aprendendo a ler e a escrever. ❤️

---

## 📚 Sobre o Projeto

- 🎮 Um jogo interativo da forca com interface gráfica.
- 👧 Pensado especialmente para crianças em processo de alfabetização.
- ✨ Com cores divertidas, efeitos visuais e imagens temáticas para tornar o aprendizado mais envolvente.
- 🧪 Criado como exercício prático para consolidar conhecimentos em Java, JavaFX e arquitetura de projetos.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **JavaFX**
- **Maven**
- **CSS para JavaFX**
- **IntelliJ IDEA / VS Code** (ambientes sugeridos)

---

## 🎨 Funcionalidades

- Exibição da palavra com letras ocultas.
- Entrada de letras com feedback visual.
- Contagem de tentativas restantes.
- Exibição das letras erradas já tentadas.
- Máscara da palavra com suporte a **acentos e hífen**.
- Animação assustadora (solicitada pela minha filha) com **imagem piscando** em caso de derrota.
- Botão para **reiniciar o jogo**.
- Interface colorida e responsiva com efeitos visuais personalizados via CSS.

---

## 📂 Estrutura do Projeto

```
forca-javafx/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/rechelab/forca/
│   │   │       ├── GameController.java
│   │   │       └── GameUI.java
│   │   └── resources/
│   │       ├── words.txt
│   │       ├── images/
│   │       	└── lost/      ← imagens de derrota
│   │       └── styles.css     ← arquivo de estilo
│
├── pom.xml
└── README.md
```

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven instalado

### Passos para executar:

1. Clone o repositório:

```bash
git clone https://github.com/Reche-Lab/forca-javafx.git
cd forca-javafx
```

2. Execute com Maven:

```bash
mvn clean javafx:run
```

> O jogo abrirá em uma janela gráfica.

---

## 🧠 Lógica de Jogo

- Letras acentuadas são aceitas com equivalentes sem acento (`á` = `a`, `é` = `e` etc).
- Caracteres como **hífens já aparecem na palavra oculta**.
- Ao errar, a imagem de derrota é escolhida **aleatoriamente** da pasta `/images/lost/`, exibida **em primeiro plano** piscando por 3 segundos e depois **sumindo**.

---

## 🖼️ Estilo Visual

- As cores e fontes foram escolhidas para agradar e engajar uma criança.
- Os botões mudam de cor ao passar o mouse.
- Fundo da janela em verde-água e componentes com estilos divertidos.

---

## 💡 Inspiração

> Criado com carinho como uma forma de unir o estudo de programação com a diversão e aprendizado da minha filha 💖

---

## 📃 Licença

Este projeto é livre para fins educacionais e pessoais.  
Sinta-se à vontade para modificar e usar como quiser.

---

## 🙌 Contribuições

Sugestões, melhorias ou ideias são bem-vindas! Abra uma issue ou envie um PR.

---

## 📸 Screenshots

[//]: # (> *&#40;Screenshot do jogo rodando&#41;*)
<div align="center">
  <img src="src/main/resources/images/screenshot/forca.gif" alt="gif do jogo" width="50%" style="margin-right: 10px;"/>
</div>

---

Feito com 💙 por [Bruno Reche](https://github.com/Reche-Lab)
