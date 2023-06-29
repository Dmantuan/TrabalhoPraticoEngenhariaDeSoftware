package ufes.presenter;

import ufes.models.DadoClima;
import ufes.view.MaxMinView;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import ufes.services.CalculoMaxMinService;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;

public class MaxMinPresenter implements IPainel {
    private Integer secondPainel = 0;
    private CalculoMaxMinService calcularMaxMin;
    private ArrayList<DadoClima> dadosClima;
    private static MaxMinPresenter instance;
    private MaxMinView view;
    
    private MaxMinPresenter(){
        this.dadosClima = new ArrayList<>();
        this.calcularMaxMin = new CalculoMaxMinService();
        this.view = new MaxMinView();
        
        this.view.setLocation(430, 180);
        
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
        
        exibirMaximasMinimas();
        }
    
    public void exibirMaximasMinimas() {     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (secondPainel == 0) {
            
            dataset.addValue(calcularMaxMin.getMaxTemperatura().getValor(), "máxima", "Temperatura");
            dataset.addValue(calcularMaxMin.getMinTemperatura().getValor(), "mínima","Temperatura");
            dataset.addValue(calcularMaxMin.getMaxUmidade().getValor(), "máxima", "Umidade");
            dataset.addValue(calcularMaxMin.getMinUmidade().getValor(), "mínima", "Umidade");
            dataset.addValue(calcularMaxMin.getMaxPressao().getValor(), "máxima", "Pressão");
            dataset.addValue(calcularMaxMin.getMinPressao().getValor(), "mínima", "Pressão");

            // Criação do gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    "Gráfico de Barras", // Título do gráfico
                    " ", // Rótulo do eixo x
                    "Valor", // Rótulo do eixo y
                    dataset, // Conjunto de dados
                    PlotOrientation.VERTICAL, // Orientação do gráfico
                    true, // Exibir legenda
                    true, // Exibir dicas de valores
                    false // Não exibir URLs
            );
            
            // OPICIONAL CODIGO
            // Obtém a referência para o enredo do gráfico
            CategoryPlot plot = chart.getCategoryPlot();
            // Define o intervalo mínimo e máximo para o eixo Y
            double minYValue = 0;  // Valor mínimo desejado
            double maxYValue = Math.max(calcularMaxMin.getMaxTemperatura().getValor(), 
                    Math.max(calcularMaxMin.getMinTemperatura().getValor(), 
                    Math.max(calcularMaxMin.getMaxUmidade().getValor(), 
                    Math.max(calcularMaxMin.getMinUmidade().getValor(),
                    Math.max( calcularMaxMin.getMaxPressao().getValor(), calcularMaxMin.getMinPressao().getValor())))));
            plot.getRangeAxis().setRange(minYValue, maxYValue + 20);
            // FIM DO CODIGO OPICIONAL

            CategoryItemLabelGenerator generator = new CategoryItemLabelGenerator() {
                @Override
                public String generateLabel(CategoryDataset dataset, int row, int column) {
                    if (row == 0)
                        return String.valueOf(calcularMaxMin.getMaxTemperatura().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else if(row == 1)
                        return String.valueOf(calcularMaxMin.getMinTemperatura().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    if (row == 2)
                        return String.valueOf(calcularMaxMin.getMaxUmidade().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else if(row == 3)
                        return String.valueOf(calcularMaxMin.getMinUmidade().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    if (row == 4)
                        return String.valueOf(calcularMaxMin.getMaxPressao().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else
                        return String.valueOf(calcularMaxMin.getMinPressao().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }

                @Override
                public String generateRowLabel(CategoryDataset cd, int i) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public String generateColumnLabel(CategoryDataset cd, int i) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            chart.getCategoryPlot().getRenderer().setBaseItemLabelGenerator(generator);
            chart.getCategoryPlot().getRenderer().setBaseItemLabelsVisible(true);

            // Criação do ChartPanel e configurações
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

            this.view.setContentPane(chartPanel);
            
            secondPainel = 1;
        }
        else {
            dataset.addValue(calcularMaxMin.getMaxTemperatura().getValor(), "máxima", "Temperatura");
            dataset.addValue(calcularMaxMin.getMinTemperatura().getValor(), "mínima","Temperatura");
            dataset.addValue(calcularMaxMin.getMaxUmidade().getValor(), "máxima", "Umidade");
            dataset.addValue(calcularMaxMin.getMinUmidade().getValor(), "mínima", "Umidade");
            dataset.addValue(calcularMaxMin.getMaxPressao().getValor(), "máxima", "Pressão");
            dataset.addValue(calcularMaxMin.getMinPressao().getValor(), "mínima", "Pressão");

            // Criação do gráfico de barras
            JFreeChart chart = ChartFactory.createBarChart(
                    "Gráfico de Barras", // Título do gráfico
                    " ", // Rótulo do eixo x
                    "Valor", // Rótulo do eixo y
                    dataset, // Conjunto de dados
                    PlotOrientation.VERTICAL, // Orientação do gráfico
                    true, // Exibir legenda
                    true, // Exibir dicas de valores
                    false // Não exibir URLs
            );
            
            CategoryItemLabelGenerator generator = new CategoryItemLabelGenerator() {
                @Override
                public String generateLabel(CategoryDataset dataset, int row, int column) {
                    if (row == 0)
                        return String.valueOf(calcularMaxMin.getMaxTemperatura().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else if(row == 1)
                        return String.valueOf(calcularMaxMin.getMinTemperatura().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    if (row == 2)
                        return String.valueOf(calcularMaxMin.getMaxUmidade().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else if(row == 3)
                        return String.valueOf(calcularMaxMin.getMinUmidade().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    if (row == 4)
                        return String.valueOf(calcularMaxMin.getMaxPressao().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    else
                        return String.valueOf(calcularMaxMin.getMinPressao().getDataMedicao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }

                @Override
                public String generateRowLabel(CategoryDataset cd, int i) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public String generateColumnLabel(CategoryDataset cd, int i) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            chart.getCategoryPlot().getRenderer().setBaseItemLabelGenerator(generator);
            chart.getCategoryPlot().getRenderer().setBaseItemLabelsVisible(true);

            // Criação do ChartPanel e configurações
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));

            this.view.setContentPane(chartPanel);
        }
    }
    
    public MaxMinView getView(){
        return this.view;
    }
}
