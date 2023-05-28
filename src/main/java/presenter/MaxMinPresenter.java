package presenter;

import models.DadoClima;
import view.MaxMinView;
import java.util.ArrayList;
import java.time.LocalDate;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import services.CalculoMaxMinService;

public class MaxMinPresenter implements IPainel {
    private Integer secondPainel = 0;
    private LocalDate dataMedicao;
    private CalculoMaxMinService calcularMaxMin;
    private ArrayList<DadoClima> dadosClima;
    private static MaxMinPresenter instance;
    private MaxMinView view;
    
    private MaxMinPresenter(){
        this.dadosClima = new ArrayList<>();
        this.calcularMaxMin = new CalculoMaxMinService();
        this.view = new MaxMinView();
        
        this.view.setVisible(true);
    }
    
    public static MaxMinPresenter getInstance(){
        if(MaxMinPresenter.instance == null){
            MaxMinPresenter.instance = new MaxMinPresenter();
        }
        return MaxMinPresenter.instance;
    }
    
    @Override
    public void atualizar(DadoClima dadoClima, String tipo) {
        if (tipo.equalsIgnoreCase("add")){
            this.dadosClima.add(dadoClima);
        }
        else{
            this.dadosClima.remove(dadoClima);
        }
        this.calcularMaxMin.calcular(dadosClima);
        
        dataMedicao = LocalDate.now();
        exibirMaximasMinimas();
        }
    
    public void exibirMaximasMinimas() {     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (secondPainel == 0) {
            // Adicionar os dados do estacaoClimatica aos datasets
            dataset.addValue(calcularMaxMin.getMaxTemperatura(), "Temperatura máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinTemperatura(), "Temperatura mínima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMaxUmidade(), "Umidade máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinUmidade(), "Umidade mínima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMaxPressao(), "Pressão máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinPressao(), "Pressão mínima", dataMedicao);

            // Criação do gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    "Gráfico de Barras", // Título do gráfico
                    "Data", // Rótulo do eixo x
                    "Valor", // Rótulo do eixo y
                    dataset, // Conjunto de dados
                    PlotOrientation.VERTICAL, // Orientação do gráfico
                    true, // Exibir legenda
                    true, // Exibir dicas de valores
                    false // Não exibir URLs
            );

            // Criação do ChartPanel e configurações
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
            
            this.view.setContentPane(chartPanel);
            
            secondPainel = 1;
        }
        else {
            dataset.addValue(calcularMaxMin.getMaxTemperatura(), "Temperatura máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinTemperatura(), "Temperatura mínima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMaxUmidade(), "Umidade máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinUmidade(), "Umidade mínima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMaxPressao(), "Pressão máxima", dataMedicao);
            dataset.addValue(calcularMaxMin.getMinPressao(), "Pressão mínima", dataMedicao);

            // Criação do gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    "Gráfico de Barras", // Título do gráfico
                    "Data", // Rótulo do eixo x
                    "Valor", // Rótulo do eixo y
                    dataset, // Conjunto de dados
                    PlotOrientation.VERTICAL, // Orientação do gráfico
                    true, // Exibir legenda
                    true, // Exibir dicas de valores
                    false // Não exibir URLs
            );
            
            // Atualizar o ChartPanel existente no JFrame interno
            ChartPanel chartPanel = new ChartPanel(chart);
            this.view.setContentPane(chartPanel);

        }
    }
    
    public MaxMinView getView(){
        return this.view;
    }
}
