package colombiansoccerleague;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class Menu extends javax.swing.JFrame {

    private ArrayList<Team> lstTeams;
    private ArrayList<Match> totalMatches;
    private int userOption;
    //private ArrayList<MatchDate> lstMatchDates;
    public static ArrayList<Match> lstMatches;
    //  Match match = new Match();
    //MatchDate matchDate = new MatchDate();
    static int countMatchDate;

    String[] COLUMNS = {"Name", "W", "L", "D", "GS", "GC", "M", "Pts"};
    String[] SCOLUMNS = {"Team1", "GoalsTeam1", "GoalsTeam2", "Team2"};
    DefaultTableModel tableModel;

    public Menu() {
        lstTeams = new ArrayList<>();
        //lstMatchDates=new ArrayList<>();
        lstMatches = new ArrayList<>();
        totalMatches = new ArrayList<>();
        initComponents();
        initTeams();
        getMatches();


    }

    private void initObjects() {
        String[][] data = new String[lstTeams.size()][8];
        for (int i = 0; i < lstTeams.size(); i++) {
            data[i][0] = lstTeams.get(i).getName();
            data[i][1] = String.valueOf(lstTeams.get(i).getWins());
            data[i][2] = String.valueOf(lstTeams.get(i).getLosses());
            data[i][3] = String.valueOf(lstTeams.get(i).getDraws());
            data[i][4] = String.valueOf(lstTeams.get(i).getGoalsScored());
            data[i][5] = String.valueOf(lstTeams.get(i).getGoalsConceded());
            data[i][6] = String.valueOf(lstTeams.get(i).getMatchesPlayed());
            data[i][7] = String.valueOf(lstTeams.get(i).getPoints());
        }
        tableModel = new DefaultTableModel(data, COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblStats.setModel(tableModel);
        tblStats.setAutoCreateRowSorter(true);
    }

    private void initSObjects() {
        var rows = totalMatches.size();
        String[][] data = new String[rows][4];
        for (int i = 0; i < rows; i++) {
            data[i][0] = totalMatches.get(i).getTeam1().getName();
            data[i][1] = String.valueOf(totalMatches.get(i).getGoals1());
            data[i][2] = String.valueOf(totalMatches.get(i).getGoals2());
            data[i][3] = totalMatches.get(i).getTeam2().getName();
        }
        tableModel = new DefaultTableModel(data, SCOLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblRecap.setModel(tableModel);
        tblRecap.setAutoCreateRowSorter(true);
    }

    public int getUserOption() {
        return userOption;
    }

    public void setUserOption(int userOption) {
        this.userOption = userOption;
    }

    public ArrayList<Team> initTeams() {
        ArrayList<String> lstStringTeams = new ArrayList<>();
        lstStringTeams.add("Nacional");
        lstStringTeams.add("Patriotas");
        lstStringTeams.add("Santa Fe");

        lstStringTeams.add("Cali");
        /*
        lstStringTeams.add("Tolima");
        lstStringTeams.add("Medellín");
        lstStringTeams.add("Patriotas");
        lstStringTeams.add("Aguilas Doradas");
        lstStringTeams.add("Millonarios");
        lstStringTeams.add("Once Caldas");*/

        for (int i = 0; i < lstStringTeams.size(); i++) {
            Team team = new Team();
            team.setName(lstStringTeams.get(i));
            lstTeams.add(team);
        }
        return lstTeams;
    }

    public void askUserScore(Team team1, Team team2, int goals1, int goals2) {
        boolean flag;
        do {
            try {
                var question = ("Insert the goals scored by " + team1.getName() + ":");
                goals1 = Integer.parseInt(JOptionPane.showInputDialog(question));
                flag = false;
            } catch (Exception e) {
                showMessageDialog(null, "Please, insert correct values");
                flag = true;
            }
        } while (flag);

        do {
            try {
                var question = ("Insert the goals scored by " + team2.getName() + ":");
                goals2 = Integer.parseInt(JOptionPane.showInputDialog(question));
                flag = false;
            } catch (Exception e) {
                System.out.println("Please, insert correct values");
                flag = true;
                showMessageDialog(null, "Please, insert correct values");
            }
        } while (flag);
    }

    public void faceOff(Team team1, Team team2) {
        Match match = new Match(team1, team2);
        int goals1 = 0;
        int goals2 = 0;
        switch (userOption) {
            case 0:
                askUserScore(team1, team2, goals1, goals2);
                match.setGoals1(goals1);
                match.setGoals2(goals2);
                if (goals1 == goals2) {
                    match.draw(team1, team2, goals1);
                } else if (goals1 < goals2) {
                    match.winner(team2, goals2, goals1);
                    match.losser(team1, goals1, goals2);
                } else {
                    match.winner(team1, goals1, goals2);
                    match.losser(team2, goals2, goals1);
                }
                totalMatches.add(match);
                break;
            case 1:
                goals1 = (int) (Math.random() * 5);
                goals2 = (int) (Math.random() * 5);
                match.setGoals1(goals1);
                match.setGoals2(goals2);
                if (goals1 == goals2) {
                    match.draw(team1, team2, goals1);

                } else if (goals1 < goals2) {
                    match.winner(team2, goals2, goals1);
                    match.losser(team1, goals1, goals2);
                } else {
                    match.winner(team1, goals1, goals2);
                    match.losser(team2, goals2, goals1);
                }
                totalMatches.add(match);
                break;
            default:
                break;
        }
    }

    public void getMatches() {
        for (int i = 0; i < lstTeams.size()-1; i++) {
            Team team1 = lstTeams.get(i);
            Team team2 = lstTeams.get(lstTeams.size()-1-i);
            var matchS = new Match(team1, team2);

            lstMatches.add(matchS);
            Collections.rotate(lstTeams.subList(1, lstTeams.size()), 1);
        }
        totalMatches=lstMatches;
    }

    public void playMatches( int a) {
        int i;

        for (i = 0; i < a; i ++) {
            Team team1 = lstMatches.get(0).getTeam1();
            Team team2 = lstMatches.get(0).getTeam2();
            faceOff(team1, team2);
            lstMatches.remove(0);
            //lstMatches.remove(1);
            team1.setMatchesPlayed(1);
            team2.setMatchesPlayed(1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStats = new javax.swing.JTable();
        lblStats = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnPlayRound = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblRecap = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRecap = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnShowWinner = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(231, 231, 231));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblStats.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Wins", "Losses", "Draws", "GS", "GC", "M", "Points"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblStats);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 470, 190));

        lblStats.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lblStats.setForeground(new java.awt.Color(183, 40, 57));
        lblStats.setText("Stats");
        jPanel1.add(lblStats, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        lblTitle.setFont(new java.awt.Font("Kristen ITC", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(183, 40, 57));
        lblTitle.setText("AllOutSoccer");

        btnPlayRound.setBackground(new java.awt.Color(231, 231, 231));
        btnPlayRound.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnPlayRound.setText("Play Round");
        btnPlayRound.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(183, 40, 57), 2, true));
        btnPlayRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayRoundActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logoSmall.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitle)
                .addGap(353, 353, 353)
                .addComponent(btnPlayRound, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPlayRound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitle)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 100));

        jPanel4.setBackground(new java.awt.Color(214, 216, 217));

        lblRecap.setFont(new java.awt.Font("Bookman Old Style", 1, 18)); // NOI18N
        lblRecap.setForeground(new java.awt.Color(183, 40, 57));
        lblRecap.setText("Recap");

        tblRecap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblRecap);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblRecap))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblRecap)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 480, 450));

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 1060, 40));

        btnShowWinner.setBackground(new java.awt.Color(231, 231, 231));
        btnShowWinner.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnShowWinner.setText("Show Winner");
        btnShowWinner.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(183, 40, 57), 2, true));
        btnShowWinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowWinnerActionPerformed(evt);
            }
        });
        jPanel1.add(btnShowWinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayRoundActionPerformed
        
        playMatches(1);
        do {
            countMatchDate++;
        } while (countMatchDate != 20);
        initObjects();
        initSObjects();

        /*if (counter < (game.getTeamsLstSize()/2) * (game.getTeamsLstSize() - 2) + 1 ){
            counter += game.getTeamsLstSize()/2; 
            game.playRound();
            //newList = game.playRound();
            //updatedLstStats();
            updatedLstStats();
        }
        else{
            if(counter>=(game.getTeamsLstSize()/2) * (game.getTeamsLstSize() - 2) + 1 
               && counter<(game.getTeamsLstSize()/2)*(game.getTeamsLstSize()-1)){
                showMessageDialog(null, "Select another option.");
                btnPlayRound.setEnabled(false);
            }
            else{
                showMessageDialog(null, "Hold on!!!" +"\n"+ "All the rounds have already been played.");
                disableButtons();
            }
        } */
    }//GEN-LAST:event_btnPlayRoundActionPerformed

    private void btnShowWinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowWinnerActionPerformed
        /*if(counter==(game.getTeamsLstSize()/2)*(game.getTeamsLstSize()-1)){
            showWinner();
            disableButtons();
        }
        else{
            showMessageDialog(null, "Hold on!!!" +"\n"+ "The tournament hasn't ended.");  
        }*/
    }//GEN-LAST:event_btnShowWinnerActionPerformed

    /* public void showWinner(){
       int maxPoints=0;
       ArrayList<String> winners = new ArrayList<>();
       for(Team e: lstTeams){
          if(e.getPoints()>maxPoints){
            maxPoints=e.getPoints();
          }
       }
       for(Team e: lstTeams){
           if(e.getPoints() == maxPoints){
               winners.add(e.getName());
           }  
       }
       if(winners.size()==1){
        showMessageDialog(null, "The winner is "+winners);
       }
       else{
        showMessageDialog(null, "The winners are "+winners);
       }
   }*/
 /*TODO Crear un alert que me muestre el ganador(podemos poner que cuando counter==)
    if(counter==game.getLstSize()/2)*(game.getLstSize()-1) do el alert con el ganador*/
 /*public void disableButtons(){
        if (counter== (game.getTeamsLstSize()/2) *(game.getTeamsLstSize()-1) ) {  
            btnPlayRound.setEnabled(false);
            btnPlayTournament.setEnabled(false);
            btnPlayMatch.setEnabled(false);
        }
    }*/
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayRound;
    private javax.swing.JButton btnShowWinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblRecap;
    private javax.swing.JLabel lblStats;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblRecap;
    private javax.swing.JTable tblStats;
    // End of variables declaration//GEN-END:variables
}
