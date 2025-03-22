import http from 'k6/http';
    import { check, sleep } from 'k6';
    import { randomIntBetween, randomItem } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

    export const options = {
      vus: 20, // usuários virtuais
      duration: '300s',
    };

    export default function () {
      // Lista de marcas de cartão disponíveis
      const brands = ['VISA', 'MASTERCARD', 'ELO', 'HIPERCARD', 'AMEX'];

      // Gerar valor aleatório entre 1000 e 100000 centavos (R$10 a R$1000)
      const amount = randomIntBetween(1000, 100000);

      // Gerar merchantId aleatório
      const merchantId = `merch_${Math.floor(Math.random() * 1000000).toString().padStart(6, '0')}`;

      const payload = JSON.stringify({
        cardToken: "tok_123456789",
        brand: randomItem(brands),
        merchantId: merchantId,
        amountTransaction: amount,
        currency: "BRL",
        isInstallment: false,
        installments: 1,
        firstInstallmentAmount: amount,
        otherInstallmentAmount: null
      });

      const params = {
        headers: {
          'Content-Type': 'application/json',
        },
      };

      const res = http.post('http://localhost:8080/card/processor', payload, params);

      check(res, {
        'status 200': (r) => r.status === 200
      });

      sleep(1);
    }