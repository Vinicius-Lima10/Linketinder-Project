let competenciasChoices = null;
let formacaoChoices = null;

async function loadContent(url) {
  try {
    const res = await fetch(url);
    if (!res.ok) throw new Error(res.statusText);
    const html = await res.text();
    const container = document.getElementById('main-content');
    container.innerHTML = html;

    // Campo de competências
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

    // Campo de formação
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

    // Formulário de candidato
    const formCandidato = container.querySelector('#formCandidato');
    if (formCandidato) {
      formCandidato.addEventListener('submit', (e) => {
        e.preventDefault();
        cadastrarCandidatoS();

        // reset normal
        formCandidato.reset();

        // limpar os Choices
        if (competenciasChoices) competenciasChoices.clearStore();
        if (formacaoChoices) formacaoChoices.clearStore();
      });
    }

    // Formulário de empresa
    const formEmpresa = container.querySelector('#formEmpresa');
    if (formEmpresa) {
      formEmpresa.addEventListener('submit', (e) => {
        e.preventDefault();
        cadastrarEmpresaS();

        formEmpresa.reset();
        if (competenciasChoices) competenciasChoices.clearStore();

      });
    }

    // Formulário de vagas
    const formVagas = container.querySelector('#formVagas');
    if (formVagas) {
      formVagas.addEventListener('submit', (e) => {
        e.preventDefault();
        cadastrarVaga();

        formVagas.reset();
        if (competenciasChoices) competenciasChoices.clearStore();

      });
    }

    // Tabelas
    const tabelaCandidatos = container.querySelector('#tabelaCandidatos');
    if (tabelaCandidatos) {
      mostrarCandidatosAnonimos();
    }

    const tabelaVagas = container.querySelector('#tabelaVagas');
    if (tabelaVagas) {
      listarVagas();
    }

  } catch (err) {
    console.error('Erro ao carregar conteúdo', url, err);
  }
}

// Navegação SPA com data-link
document.addEventListener('click', function(e) {
  const link = e.target.closest('[data-link]');
  if (!link) return;      

  e.preventDefault();          
  const url = link.getAttribute('data-link');
  loadContent(url);           
});
