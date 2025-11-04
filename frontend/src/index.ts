import { cadastrarCandidatoS } from "./components/CandidatoComponent";
import { cadastrarEmpresaS } from "./components/EmpresaComponent";
import { mostrarCandidatosAnonimos } from "./components/EmpresaComponent";
import { cadastrarVagaS, listarVagasS } from "./components/VagaComponent";

(window as any).cadastrarCandidatoS = cadastrarCandidatoS;
(window as any).cadastrarEmpresaS = cadastrarEmpresaS;
(window as any).mostrarCandidatosAnonimos = mostrarCandidatosAnonimos;
(window as any).cadastrarVaga = cadastrarVagaS;
(window as any).listarVagas = listarVagasS

