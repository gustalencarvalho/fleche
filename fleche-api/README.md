# Fleche 💘

📲 **Fluxo da Aplicação**

1️⃣ **Usuário faz check-in no location**

2️⃣ **Vê outras pessoas no location**

3️⃣ **Envia um Fleche para alguém**

4️⃣ **Se for aceito, abre o chat**

5️⃣ **Pode oferecer um drink pelo app**

6️⃣ **Se aceito, o pedido vai para a cozinha do location**

7️⃣ **Pagamento pode ser na comanda ou online**

## **Regras de Negócio**

Aqui estão algumas regras principais que o app deve seguir:

### **1️⃣ Cadastro e Login**

- O usuário pode se cadastrar usando **telefone, e-mail ou redes sociais**.
- Deve confirmar a conta via **SMS ou e-mail**.
- Precisa fornecer **nome, foto, gênero, idade e preferências**.
- Pode ativar/desativar o modo "visível no location".

### **2️⃣ Check-in no Bar**

- O usuário pode **selecionar manualmente** o location onde está ou ser identificado via **GPS/QR Code**.
- O usuário **só pode visualizar** e interagir com outras pessoas se estiver em um location cadastrado no app.
- Check-in tem **tempo de expiração** (exemplo: 4h sem atividade = check-out automático).

### **3️⃣ Sistema de Match**

- O usuário pode ver outras pessoas no location e **enviar uma solicitação de match**.
- A outra pessoa pode **aceitar ou recusar**.
- Se aceitar, abre um chat privado.
- O usuário pode **bloquear** ou **denunciar** outro usuário.

### **4️⃣ Pedidos de Drinks**

- No chat, um usuário pode **oferecer um drink** para o outro.
- O drink **só aparece** se estiver disponível no cardápio do location.
- O receptor pode **aceitar ou recusar** o drink.
- Se aceitar, **o valor é adicionado à comanda do pagante** e o pedido é enviado ao location.
- O location recebe **notificação do pedido** e prepara a bebida.

### **5️⃣ Pagamentos**

- O usuário pode pagar:
    - Na **comanda física do location** (o app apenas adiciona pedidos à conta).
    - Via **pagamento online** (cartão de crédito, Pix, carteira digital).
- O location recebe repasse do pagamento se for feito via app.
- Possibilidade de **cashback ou descontos** para incentivar pedidos.