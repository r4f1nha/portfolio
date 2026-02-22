console.log("app.js carregou ✅");
const URL = 'http://localhost:8080/projects';

const experiences = [
  {
    role: "Analista de Sistemas",
    details: "Integrações entre sistemas, automações e suporte a operações críticas.",
    stack: ["Bitrix", "Netsuite", "Azure DevOps"]
  },
  {
    role: "Java Developer (Sprint)",
    details: "User stories, correção de bugs e melhorias contínuas durante sprints.",
    stack: ["Java", "Spring Boot", "Git"]
  }
];

async function chamarApi() {
  const resp = await fetch(URL);

  if (!resp.ok) {
    console.error("Erro na API:", resp.status, resp.statusText);
    return [];
  }

  return await resp.json();
}

function renderProjects(projects) {
  const container = document.getElementById("project-list");
  container.innerHTML = projects.map(p => `
    <div class="card">
      <h3>${p.title}</h3>
      <p>${p.description}</p>
      <div class="tags">
        ${(p.tags ?? []).map(t => `<span class="tag">${t}</span>`).join("")}
      </div>
    </div>
  `).join("");
}

function renderExperiences() {
  const container = document.getElementById("experience-list");
  container.innerHTML = experiences.map(e => `
    <div class="card">
      <h3>${e.role}</h3>
      <p>${e.details}</p>
      <div class="tags">
        ${e.stack.map(s => `<span class="tag">${s}</span>`).join("")}
      </div>
    </div>
  `).join("");
}

// Boot da página: carrega API e renderiza
(async function init() {
  const projects = await chamarApi();
  console.log("init rodou ✅");
console.log("projects:", projects);
  renderProjects(projects);
  renderExperiences();
})();