import { Candidato } from "../models/Candidato";
import {
  Chart,
  BarController,
  BarElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
} from "chart.js";

Chart.register(
  BarController,
  BarElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
);

export function graficoCandidatos(candidatos: Candidato[]) {
  const ctx = document.getElementById("graficoCompetencias") as HTMLCanvasElement;
  const contagem: Record<string, number> = {};

  candidatos.forEach(c => {
    c.competencias
      .map(comp => comp.trim())
      .filter(comp => comp !== "") 
      .forEach(comp => {
        contagem[comp] = (contagem[comp] || 0) + 1;
      });
  });

  const labels = Object.keys(contagem);
  const data = Object.values(contagem);

  if (labels.length === 0) {
    return;
  }

  new Chart(ctx, {
    type: "bar",
    data: {
      labels: labels,
      datasets: [{
        label: "Quantidade de Candidatos",
        data: data,
        backgroundColor: "rgba(54, 162, 235, 0.7)",
        borderColor: "rgba(54, 162, 235, 1)",
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      plugins: {
        title: {
          display: true,
          text: "Candidatos x Competências",
          font: {
            size: 18,
            weight: "bold"
          }
        },
        legend: {
          display: false
        }
      },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            precision: 0
          }
        }
      }
    }
  });
}
