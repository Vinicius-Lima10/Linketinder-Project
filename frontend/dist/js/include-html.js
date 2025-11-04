let competenciasChoices = null;
let formacaoChoices = null;

async function loadContent(url) {
  try {
    const res = await fetch(url);
    if (!res.ok) throw new Error(res.statusText);
    const html = await res.text();
    const container = document.getElementById('main-content');
    container.innerHTML = html;

    const competenciasInputEl = container.querySelector('#competencias');
    if (competenciasInputEl) {
      competenciasChoices = new Choices(competenciasInputEl, {
        removeItemButton: true,
        delimiter: ',',
        editItems: true,
        addItems: true,
        choices: [],
        noChoicesText: '',
        placeholderValue: 'Digite as competências',
      });
    }

    const formacaoInputEl = container.querySelector('#formacao');
    if (formacaoInputEl) {
      formacaoChoices = new Choices(formacaoInputEl, {
        removeItemButton: true,
        delimiter: ',',
        editItems: true,
        addItems: true,
        choices: [],
        noChoicesText: '',
        placeholderValue: 'Digite suas formações',
      });
    }

const formCandidato = container.querySelector('#formCandidato');
if (formCandidato) {
  formCandidato.addEventListener('submit', (e) => {
    e.preventDefault()

    formCandidato.querySelectorAll(".form-control").forEach((input) => {
      limparErro(input)
    });

    try {
      cadastrarCandidatoS()
      formCandidato.reset()

      if (competenciasChoices) competenciasChoices.clearStore()
      if (formacaoChoices) formacaoChoices.clearStore()

    } catch (err) {
      const mensagem = err.message;
      console.log(mensagem)
      const mensagens = err.message.split(" | ");
      for (const mensagem of mensagens) {
        if (mensagem.includes("Nome")) setErro(document.getElementById("nomeCandidato"), mensagem);
        if (mensagem.includes("Sobrenome")) setErro(document.getElementById("sobrenomeCandidato"), mensagem);
        if (mensagem.includes("CPF")) setErro(document.getElementById("cpf"), mensagem);
        if (mensagem.includes("E-mail")) setErro(document.getElementById("email"), mensagem);
        if (mensagem.includes("Telefone")) setErro(document.getElementById("telefone"), mensagem);
        if (mensagem.includes("LinkedIn")) setErro(document.getElementById("linkedin"), mensagem);
        if (mensagem.includes("CEP")) setErro(document.getElementById("cep"), mensagem);
        if (mensagem.includes("Competência")) setErro(document.getElementById("competencias"), mensagem);
      }
    }
  });
}

const formEmpresa = container.querySelector('#formEmpresa')
if (formEmpresa) {
  formEmpresa.addEventListener('submit', (e) => {
    e.preventDefault();

    formEmpresa.querySelectorAll(".form-control").forEach((input) => {
      limparErro(input);
    });

    try {
      const empresa = {
        nome: document.getElementById("nomeEmpresa").value,
        cnpj: document.getElementById("cnpj").value,
        email: document.getElementById("email").value,
        pais: document.getElementById("pais").value,
        estado: document.getElementById("estado").value,
        cep: document.getElementById("cep").value,
        descricao: document.getElementById("descricao").value,
        competencias: competenciasChoices ? competenciasChoices.getValue(true) : [],
      };

      cadastrarEmpresaS(empresa);

      formEmpresa.reset();
      if (competenciasChoices) competenciasChoices.clearStore();

    } catch (err) {
      console.log(err.message);

      const mensagens = err.message.split(" | ");

      for (const mensagem of mensagens) {
        if (mensagem.includes("Nome")) {
          const input = document.getElementById("nomeEmpresa");
          if (input) setErro(input, mensagem);
        }
        if (mensagem.includes("CNPJ")) {
          const input = document.getElementById("cnpj");
          if (input) setErro(input, mensagem);
        }
        if (mensagem.includes("E-mail")) {
          const input = document.getElementById("email");
          if (input) setErro(input, mensagem);
        }
        if (mensagem.includes("CEP")) {
          const input = document.getElementById("cep");
          if (input) setErro(input, mensagem);
        }
        if (mensagem.includes("Competência")) {
          const input = document.getElementById("competencias");
          if (input) setErro(input, mensagem);
        }
      }
    }
  });
}

    const formVagas = container.querySelector('#formVagas')
    if (formVagas) {
      formVagas.addEventListener('submit', (e) => {
        e.preventDefault();
        cadastrarVaga();

        formVagas.reset();
        if (competenciasChoices) competenciasChoices.clearStore();

      });
    }

    const tabelaCandidatos = container.querySelector('#tabelaCandidatos')
    if (tabelaCandidatos) {
      mostrarCandidatosAnonimos();
    }

    const tabelaVagas = container.querySelector('#tabelaVagas')
    if (tabelaVagas) {
      listarVagas()
    }

  } catch (err) {
    console.error('Erro ao carregar conteúdo', url, err)
  }
}


document.addEventListener('click', function(e) {
  const link = e.target.closest('[data-link]')
  if (!link) return;      

  e.preventDefault();          
  const url = link.getAttribute('data-link')
  loadContent(url);           
});




function setErro(input, mensagem) {
  const choicesContainer = input.closest(".col-sm-10")?.querySelector(".choices")
  if (choicesContainer) {
    choicesContainer.classList.add("is-invalid")
  } else {
    input.classList.add("is-invalid")
  }

  const feedback = input.closest(".col-sm-10")?.querySelector(".invalid-feedback")
  if (feedback) {
    feedback.textContent = mensagem;
  }
}

function limparErro(input) {
  const choicesContainer = input.closest(".col-sm-10")?.querySelector(".choices")
  if (choicesContainer) {
    choicesContainer.classList.remove("is-invalid")
  } else {
    input.classList.remove("is-invalid")
  }

  const feedback = input.closest(".col-sm-10")?.querySelector(".invalid-feedback")
  if (feedback) {
    feedback.textContent = ""
  }
}
