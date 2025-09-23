import { cadastrarCandidatoS } from "./services/CandidatoService";
import { cadastrarEmpresaS } from "./services/EmpresaService";
import { mostrarCandidatosAnonimos } from "./services/EmpresaService";
import { cadastrarVagaS, listarVagasS } from "./services/VagaService";

(window as any).cadastrarCandidatoS = cadastrarCandidatoS;
(window as any).cadastrarEmpresaS = cadastrarEmpresaS;
(window as any).mostrarCandidatosAnonimos = mostrarCandidatosAnonimos;
(window as any).cadastrarVaga = cadastrarVagaS;
(window as any).listarVagas = listarVagasS

