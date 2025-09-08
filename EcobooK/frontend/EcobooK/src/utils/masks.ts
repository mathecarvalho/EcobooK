// Helpers
function getInputValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
}

function setInputValue(event: Event, value: string): void {
    (event.target as HTMLInputElement).value = value;
}

// CPF (###.###.###-##)
export function maskCPF(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "").slice(0, 11);

    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d{1,2})$/, "$1-$2");

    setInputValue(event, value);
}

// Data de Nascimento (dd/mm/aaaa)
export function maskDate(event: Event) {
    let value = getInputValue(event).replace(/\D/g, ""); // só números
    value = value.slice(0, 8); // máximo 8 dígitos (ddmmaaaa)

    if (value.length > 2) {
        value = value.replace(/(\d{2})(\d)/, "$1/$2");
    }
    if (value.length > 5) {
        value = value.replace(/(\d{2})\/(\d{2})(\d{1,4})/, "$1/$2/$3");
    }
    setInputValue(event, value);
}

// Telefone sem DDD (#####-#### ou ####-####)
export function maskPhone(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "");

    if (value.length > 8) {
        // celular: 9 dígitos → 5 + 4
        value = value.slice(0, 9);
        value = value.replace(/(\d{5})(\d{0,4})/, "$1-$2");
    } else {
        // fixo: 8 dígitos → 4 + 4
        value = value.slice(0, 8);
        value = value.replace(/(\d{4})(\d{0,4})/, "$1-$2");
    }
    setInputValue(event, value);
}

// CEP (#####-###)
export function maskCEP(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "").slice(0, 8);
    value = value.replace(/(\d{5})(\d)/, "$1-$2");
    setInputValue(event, value); // atualiza o input diretamente
}

// Cartão (#### #### #### ####)
export function maskCard(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "").slice(0, 16);
    value = value.replace(/(\d{4})(?=\d)/g, "$1 ");
    setInputValue(event, value); // atualiza o input
}

// Validade (MM/AA)
export function maskValidade(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "").slice(0, 4);
    value = value.replace(/(\d{2})(\d)/, "$1/$2");
    setInputValue(event, value);
}
// CVV (###)
export function maskCVV(event: Event) {
    let value = getInputValue(event).replace(/\D/g, "").slice(0, 3);
    setInputValue(event, value);
}