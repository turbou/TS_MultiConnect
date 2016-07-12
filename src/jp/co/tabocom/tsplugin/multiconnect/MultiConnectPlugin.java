package jp.co.tabocom.tsplugin.multiconnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.PreferenceStore;

import jp.co.tabocom.teratermstation.TeratermStationShell;
import jp.co.tabocom.teratermstation.model.TargetNode;
import jp.co.tabocom.teratermstation.plugin.TeratermStationPlugin;
import jp.co.tabocom.teratermstation.ui.action.TeratermStationAction;
import jp.co.tabocom.teratermstation.ui.action.TeratermStationContextMenu;

public class MultiConnectPlugin implements TeratermStationPlugin {

    @Override
    public PreferencePage getPreferencePage() {
        return new MultiConnectPreferencePage();
    }

    @Override
    public void initialize() throws Exception {
    }

    @Override
    public void teminate(PreferenceStore preferenceStore) throws Exception {
    }

    @Override
    public List<TeratermStationContextMenu> getActions(TargetNode[] nodes, TeratermStationShell shell) {
        if (nodes[0].getIpAddr() == null) {
            return null;
        }
        MultiConnectAction action = new MultiConnectAction(nodes, null, shell);
        TeratermStationContextMenu menu = new TeratermStationContextMenu();
        menu.addAction(action);
        return new ArrayList<TeratermStationContextMenu>(Arrays.asList(menu));
    }

    @Override
    public List<TeratermStationAction> getBulkActions(TargetNode[] nodes, TeratermStationShell shell) {
        return null;
    }

    @Override
    public List<TeratermStationContextMenu> getDnDActions(TargetNode[] nodes, Object value, TeratermStationShell shell) {
        return null;
    }
}
