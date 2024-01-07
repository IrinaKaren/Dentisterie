//    public static List<Prioriter> getDentsTraiter(double argent, String option, int idclient) throws Exception {
//        List<Prioriter> dents = new ArrayList<>();
//        List<Prioriter> listdents = Prioriter.getPriorite(option, idclient);
//        double cout = 0;
//
//        for (int i = 0; i < listdents.size(); i++) { 
//            if (cout < argent) {
//                if (listdents.get(i).getType().equalsIgnoreCase("traitement")) {
//                    cout += listdents.get(i).getCout_traitement();
//                    dents.add(listdents.get(i));
//                }
//                if (listdents.get(i).getType().equalsIgnoreCase("remplacement")) {
//                    cout += listdents.get(i).getCout_remplacement();
//                    dents.add(listdents.get(i));
//                }
//            }
//        }
//        return dents;
//    }

//
//public static List<Prioriter> getDentsTraiter(double argent, String option, int idclient) throws Exception {
//        List<Prioriter> dents = new ArrayList<>();
//        List<Prioriter> listdents = Prioriter.getPriorite(option, idclient);
//        double cout = 0;
//
//        for (int i = 0; i < listdents.size(); i++) { 
//            if(listdents.get(i).getCout_remplacement() <= argent ) {
//                    cout = listdents.get(i).getCout_remplacement();
//                    listdents.get(i).setType("remplacement");
//                    listdents.get(i).setCout(listdents.get(i).getCout_remplacement());
//                    dents.add(listdents.get(i));
//                    argent -= cout;
//            }
//            else if(listdents.get(i).getCout_traitement() <= argent ){
//                cout = listdents.get(i).getCout_traitement();
//                listdents.get(i).setType("traitement");
//                listdents.get(i).setCout(listdents.get(i).getCout_traitement());
//                dents.add(listdents.get(i));
//                argent -= cout;
//            }
//            else{
//                for(int j=i-1 ;j>=0; j--){
//                    if(listdents.get(j).getType().equalsIgnoreCase("remplacement")){
//                        listdents.get(j).setType("traitement");
//                        argent +=  listdents.get(j).getCout() - listdents.get(j).getCout_traitement();
//                        listdents.get(j).setCout(listdents.get(j).getCout_traitement());
//                        if(argent >= listdents.get(i).getCout_traitement()){
//                            break;
//                        }
//                    }
//                }
//                if(argent < listdents.get(i).getCout_traitement())break;
//                i=i-1;
//                continue;
//            }
//        }
//        return dents;
//    }